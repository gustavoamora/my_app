;;; namespace + requisição das libraries:
(ns tsp.core
  (:require [clojure.math.combinatorics :refer [permutations]]))

;;; def. das cidades e suas coordenadas:
(def cidades
  [{:nome "A" :coordenadas [0 0]}
   {:nome "B" :coordenadas [9 3]}
   {:nome "C" :coordenadas [1 2]}
   {:nome "D" :coordenadas [4 1]}
   {:nome "E" :coordenadas [1 1]}
   {:nome "F" :coordenadas [5 3]}
   {:nome "G" :coordenadas [3 1]}
   {:nome "H" :coordenadas [5 2]}
   {:nome "I" :coordenadas [4 4]}
   {:nome "J" :coordenadas [7 5]}
   {:nome "K" :coordenadas [8 4]}
   {:nome "L" :coordenadas [7 5]}
   
   ])

;;; função p/ cálculo da distância euclidiana:
(defn distancia-euclidiana [coord1 coord2]
  (Math/sqrt (reduce + (map (fn [p q] (Math/pow (- p q) 2)) coord1 coord2))))

;;; função p/ encontrar a cidade dentro da lista
(defn encontrar-cidade [nome]
  (first (filter #(= (:nome %) nome) cidades)))

;;; cálculo da dist. entre duas cidades
(defn distancia-entre-cidades [cidade1 cidade2]
  (let [coord1 (:coordenadas (encontrar-cidade cidade1))
        coord2 (:coordenadas (encontrar-cidade cidade2))]
    (distancia-euclidiana coord1 coord2)))

;;; cálculo do custo total do caminho
(defn calcular-custo [caminho]
  (reduce + (map (fn [[a b]] (distancia-entre-cidades a b)) (partition 2 1 caminho))))

;;; implementação do algoritmo de força bruta
(defn tsp-forca-bruta [cidade-inicial nomes-cidades]
  (let [cidades-sem-inicial (filter #(not= % cidade-inicial) nomes-cidades)
        inicio (System/currentTimeMillis)
        permutacoes (map #(cons cidade-inicial %) (permutations cidades-sem-inicial))
        caminhos-custos (map #(vector % (calcular-custo %)) permutacoes)
        melhor-caminho (apply min-key second caminhos-custos)
        fim (System/currentTimeMillis)]
    {:caminho (first melhor-caminho)
     :custo (second melhor-caminho)
     :tempo (str (- fim inicio) " ms")
     :iteracoes (count permutacoes)}))

;;; implementação do algoritmo guloso
(defn tsp-guloso [cidade-inicial nomes-cidades]
  (let [inicio (System/currentTimeMillis)
        [caminho custo] (loop [atual cidade-inicial
                               nao-visitadas (disj (set nomes-cidades) cidade-inicial)
                               caminho [cidade-inicial]
                               contador 0]
                           (if (empty? nao-visitadas)
                             [caminho (calcular-custo caminho)]
                             (let [proxima (apply min-key (fn [c] (distancia-entre-cidades atual c)) nao-visitadas)]
                               (recur proxima (disj nao-visitadas proxima) (conj caminho proxima) (inc contador)))))
        fim (System/currentTimeMillis)]
    {:caminho caminho 
     :custo custo 
     :tempo (str (- fim inicio) " ms") 
     :iteracoes (count caminho)}))

;;; funcao para obter entradas
(defn obter-entrada-usuario []
  (println "Digite o número de cidades (entre 1 e 12):")
  (let [num-cidades (Integer/parseInt (read-line))]
    (println "Escolha o algoritmo ('forca-bruta' ou 'guloso'):")
    (let [algoritmo (read-line)]
      [num-cidades algoritmo])))

;;; main
(defn -main []
  (let [[num-cidades algoritmo] (obter-entrada-usuario)
        cidades-selecionadas (take num-cidades cidades)
        nomes-cidades (map :nome cidades-selecionadas)
        resultado (cond
                    (= algoritmo "forca-bruta") (tsp-forca-bruta "A" nomes-cidades)
                    (= algoritmo "guloso") (tsp-guloso "A" nomes-cidades))]

    (println "Resultado:")
    (println "Caminho:" (resultado :caminho))
    (println "Distância Total:" (resultado :custo))
    (println "Tempo de Execução:" (resultado :tempo))
    (println "Número de Iterações:" (resultado :iteracoes))))

(-main)