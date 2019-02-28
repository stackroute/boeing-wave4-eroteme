FROM node:carbon

WORKDIR /app

COPY . .

RUN ls -la

RUN npm install

RUN npm run build

RUN npm install -g http-server

WORKDIR /app/dist/AngularService

CMD ["http-server", "-p", "8000"]
