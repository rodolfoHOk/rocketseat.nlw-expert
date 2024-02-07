import fastify from 'fastify';
import { createPool } from './routes/create-poll';
import { getPool } from './routes/get-poll';

const PORT = 3333;

const app = fastify();

app.get('/hello', () => {
  return 'Hello NLW';
});

app.register(createPool);
app.register(getPool);

app
  .listen({ port: PORT })
  .then(() => console.log(`HTTP server in running on port: ${PORT}`));
