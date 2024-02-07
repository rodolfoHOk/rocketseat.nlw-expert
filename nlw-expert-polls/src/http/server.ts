import fastify from 'fastify';
import cookie from '@fastify/cookie';
import { createPool } from './routes/create-poll';
import { getPool } from './routes/get-poll';
import { voteOnPoll } from './routes/vote-on-poll';

const PORT = 3333;

const app = fastify();

app.register(cookie, {
  secret: process.env.COOKIE_SECRET,
  hook: 'onRequest',
});

app.get('/hello', () => {
  return 'Hello NLW';
});

app.register(createPool);
app.register(getPool);
app.register(voteOnPoll);

app
  .listen({ port: PORT })
  .then(() => console.log(`HTTP server in running on port: ${PORT}`));
