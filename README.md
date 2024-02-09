7# Solução do Problema do Caixeiro Viajante em Clojure

Este projeto fornece uma implementação do algoritmo para resolver o Problema do Caixeiro Viajante (TSP) usando Clojure. Ele inclui duas abordagens: uma solução de força bruta e uma heurística gulosa. As duas soluções podem ser configuradas para resolver o problema com até 12 cidades.

## Funcionalidades

- Cálculo da distância total do percurso entre um conjunto de cidades.
- Implementação de dois algoritmos para resolver o TSP:
  - Algoritmo de Força Bruta
  - Heurística Gulosa
- Interface interativa para escolha do número de cidades e do algoritmo.

## Como Executar

1. **Configuração do Ambiente:**
   Certifique-se de ter o [Leiningen](https://leiningen.org/) instalado em seu sistema. Leiningen é uma ferramenta de automação de projetos para Clojure.
   Dependências: Clojure "1.10.3" - https://clojure.org/
                 Math combinatorics "0.2.0" - https://cljdoc.org/d/org.clojure/math.combinatorics/0.2.0/api/clojure.math.combinatorics


2. **Execução do Projeto:**
   Para executar o projeto, navegue até o diretório raiz do projeto e use o seguinte comando no terminal:

   ```bash
   lein run
3. **Entradas**
   Após a execução, o programa irá solicitar que você insira o número de cidades (entre 7 e 12) e escolha o algoritmo (força bruta ou guloso).

## Exemplos de uso
Digite o número de cidades (entre 1 e 12):
> 8
Escolha o algoritmo ('forca-bruta' ou 'guloso'):
> guloso
Resultado:
Caminho: [A B C ...]
Distância Total: X.XX
Tempo de Execução: XXX ms
Número de Iterações: XX