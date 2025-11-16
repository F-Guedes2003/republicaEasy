# republicaEasy

# Inicialização da aplicação

Para iniciar a aplicação, certifique-se de que você está no diretório raiz do projeto e rode o comando:

```
docker compose up --build
```

Esse comando vai subir o conteiner do postgres necessário para que a aplicação funcione, caso você já tenha a imagem usada na construção do conteiner baixada no seu dispositivo, o comando *--build* é desnecessário.

Quando o conteiner estiver inicializado você verá uma mensagem semelhante à essa no console:

<img width="882" height="45" alt="Screenshot from 2025-11-16 16-20-55" src="https://github.com/user-attachments/assets/c1bb7f2a-ba7c-43ab-a527-cf2a139b14fa" />

Com o conteiner de pé é só subir a aplicação Spring Boot
