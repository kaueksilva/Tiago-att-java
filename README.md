# Professor Allocation API

## Integrantes do grupo

1. Kauê Silva Nascimento
2. Caio Braz do Lago
3. Diogo Sant Ana de Vasconcelos

## O que cada integrante fez

### Kauê Silva Nascimento
- Implementou a camada de DTOs e mapeadores para os endpoints de `Department`, `Professor`, `Course` e `Allocation`.
- Centralizou o tratamento de erros com `@RestControllerAdvice` e `ErrorResponse`, eliminando a necessidade de `try/catch` repetidos nos controllers.
- Ajustou as validações de entrada com `@Valid` e anotações nos records de request.

### Caio Braz do Lago
- Reestruturou os controllers para expor apenas DTOs e não entidades JPA nas respostas da API.
- Revisou os serviços para manter consistência de regras de negócio, como validação de horário final, relacionamento de professor/curso e tratamento de entidades não encontradas.
- Colaborou na organização dos endpoints e na documentação Swagger.

### Diogo Sant Ana de Vasconcelos
- Contribuiu com a padronização das respostas e mensagens de erro do sistema.
- Validou os fluxos de CRUD e a integridade de dados entre professor, departamento, curso e alocação.
- Apoiou a revisão final da API, ajustando o comportamento esperado em casos de not found, invalid request e conflito de agenda.

## Comentários

Este projeto foi organizado com foco em API REST seguindo boas práticas de separação de responsabilidades:
- camadas de controller, service, mapper e repository bem definidas;
- uso de DTOs para evitar exposição direta de entidades e problemas de serialização;
- validação automática de entrada com `jakarta.validation`;
- respostas padronizadas em JSON com corpo de erro explícito.

A entrega foi pensada para manter a aplicação mais segura, previsível e de fácil manutenção.
