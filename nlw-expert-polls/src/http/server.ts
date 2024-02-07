import fastify from 'fastify';

const PORT = 3333;

const app = fastify();

app.get('/hello', () => {
  return 'Hello NLW';
});

app
  .listen({ port: PORT })
  .then(() => console.log(`HTTP server in running on port: ${PORT}`));
