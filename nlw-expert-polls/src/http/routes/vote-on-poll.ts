import { FastifyInstance } from 'fastify';
import z from 'zod';
import { prisma } from '../../lib/prisma';
import { randomUUID } from 'crypto';
import { redis } from '../../lib/redis';
import { votingPubSub } from '../../utils/voting-pub-sub';

export async function voteOnPoll(app: FastifyInstance) {
  app.post('/polls/:pollId/votes', async (request, reply) => {
    const voteOnPollParams = z.object({
      pollId: z.string().uuid(),
    });
    const { pollId } = voteOnPollParams.parse(request.params);

    const voteOnPollBody = z.object({
      pollOptionId: z.string().uuid(),
    });
    const { pollOptionId } = voteOnPollBody.parse(request.body);

    let { sessionId } = request.cookies;
    if (sessionId) {
      const userPreviousVoteOnPoll = await prisma.vote.findUnique({
        where: {
          sessionId_pollId: {
            sessionId,
            pollId,
          },
        },
      });

      if (userPreviousVoteOnPoll) {
        if (userPreviousVoteOnPoll.pollOptionId === pollOptionId) {
          return reply
            .status(400)
            .send({ message: 'You have already voted on this poll' });
        }
        await prisma.vote.delete({
          where: {
            id: userPreviousVoteOnPoll.id,
          },
        });
        const votes = await redis.zincrby(
          pollId,
          -1,
          userPreviousVoteOnPoll.pollOptionId
        );

        votingPubSub.publish(pollId, {
          pollOptionId: userPreviousVoteOnPoll.pollOptionId,
          votes: Number(votes),
        });
      }
    } else {
      sessionId = randomUUID();
      reply.setCookie('sessionId', sessionId, {
        path: '/',
        maxAge: 60 * 60 * 24 * 30, // 30 days
        signed: true,
        httpOnly: true,
      });
    }

    await prisma.vote.create({
      data: {
        sessionId,
        pollId,
        pollOptionId,
      },
    });

    const votes = await redis.zincrby(pollId, 1, pollOptionId);

    votingPubSub.publish(pollId, {
      pollOptionId,
      votes: Number(votes),
    });

    return reply.status(201).send();
  });
}
