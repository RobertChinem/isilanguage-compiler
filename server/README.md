# Servidor 

## Endpoints

### GET /
Exibe o editor de código.

### GET /api/example
Retorna um exemplo de código em isi-language.

#### Retorno
| Parâmetro | Tipo |
| - | - |
| codeExample | string |

### POST /api/compileIsiLang
Retorna o código java equivalente do código (isi-language) enviado.

#### Body
| Parâmetro | Tipo |
| - | - |
| code | string |

#### Retorno
| Parâmetro | Tipo |
| - | - |
| output? | string |
| code? | string |
| error? | string |
| success | boolean |

### POST api/run/java
Retorna o resultado da execução do código (java) enviado.

#### Body
| Parâmetro | Tipo |
| - | - |
| code | string |
| input | string |

#### Retorno
| Parâmetro | Tipo |
| - | - |
| success | boolean |
| error? | string |
| output | string |
