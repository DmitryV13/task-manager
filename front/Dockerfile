FROM node:22.14.0-alpine3.20
LABEL authors="dmitry voicu 2025"
WORKDIR /app
COPY package*.json .
RUN npm install
COPY . .
EXPOSE 9000
CMD ["npm", "run", "webapp:dev"]


