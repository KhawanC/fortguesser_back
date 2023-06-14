# Plataforma Social

Conceito e desenvolvimento ainda em processos iniciais.

## Instalação

O core do projeto atualmente utiliza Java 17 e Postgres. Para testar o Java localmente será necessário instalar o JDK do Java 17, já o Postgres também é possível fazer sua instalação manual porém recomendo utilizar a imagem Docker disponibilizada em [resources](src/main/resources/docker-compose.yml).

```bash
docker-compose up -d postgres
```

- As credenciais para acesso ao banco estão dentro do arquivo da imagem ('POSTGRES_USER' e 'POSTGRES_PASSWORD')
- Futuramente será disponibilizado uma imagem para subir a API com o Docker.

## Variáveis de ambiente

Atualmente possuímos 2 possíveis ambientes da aplicação, um local e outro na nuvem utilizando o [Railway](https://railway.app). Portando para evitar gambiarra no código para apontar para os diferentes ambientes utilizamos as variáveis de ambiente. Sendo elas:

- DB_HOST
- DB_NAME
- DB_PASSWORD
- DB_PORT
- DB_USERNAME
- JWT_SECRET
- PRODUCTION_API_URL

## Documentação e ambiente de produção

No momento estou utilizando a versão grátis do Railway, portando tenho 500 horas de uso da plataforma ou $5 de uso segundo os critérios deles (não entendi muito bem commo funciona), eu tenho desligado e ligado ela quando preciso testar algo, então caso ela esteja fora do ar você já sabe o que pode ser. Se quiser posso te adicionar no ambiente de lá para você acessar as configurações.

Quando no ar, nosso link para a API é:

[backend-production-e662.up.railway.app](https://backend-production-e662.up.railway.app/plataforma-social)

[Documentacao](https://backend-production-e662.up.railway.app/plataforma-social/docs.html)

Caso tenha dúvidas em algum endpoint ou em alguma regra de negócio no backend é só chamar.

## Contribuições no código

Ainda estou desenvolvendo os testes unitários no código para garantia de entrega e evitar que as funcionalidades quebrem com o aumento da complexidade da API, mas ainda estou desenvolvendo isso. Fique à vontade para fazer pull requests ou de postar alguma issue (mensagens no whatsapp podem ser mais rápidas).
