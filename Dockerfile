FROM node:17

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install -g http-server

COPY . .

EXPOSE 8083

CMD ["npm", "run", "dev"]