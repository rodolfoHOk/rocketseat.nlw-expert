import fastify from 'fastify';
import cookie from '@fastify/cookie';
import websocket from '@fastify/websocket';
import { createPool } from './routes/create-poll';
import { getPool } from './routes/get-poll';
import { voteOnPoll } from './routes/vote-on-poll';
import { pollResults } from './ws/poll-results';

const PORT = 3333;

const app = fastify();

app.register(cookie, {
  secret: process.env.COOKIE_SECRET,
  hook: 'onRequest',
});

app.register(websocket);

app.get('/hello', () => {
  return 'Hello NLW';
});

app.register(createPool);
app.register(getPool);
app.register(voteOnPoll);
app.register(pollResults);

app
  .listen({ port: PORT })
  .then(() => console.log(`HTTP server in running on port: ${PORT}`));
