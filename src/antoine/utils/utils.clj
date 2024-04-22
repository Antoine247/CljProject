(ns antoine.utils.utils
  ( :require [java-time.api :as jt]))

(defn vector-doble?
  [xs]
  (and (vector? xs) (every? vector? xs)))

(defn vector-mapa?
  [xs]
  (and (vector? xs) (every? map? xs)))

(defn fecha-actual
  []
  (-> (jt/format "yyyMMdd" (jt/local-date-time))
      Integer/parseInt))

(defn hora-actual
  []
  (-> (jt/format "Hmm" (jt/local-date-time))
      Integer/parseInt))

(defn histclirand
  "genera un numero random entre 500.000 y 800.000"
  []
  (+ 500000 (rand-int 300000)))

(defn remover-digito-verificador
  [legajo]
  (cond
    (not (number? legajo)) 0
    (== 0 legajo) legajo
    (< legajo 10) legajo
    :else (->> legajo
               str
               char-array
               butlast
               (apply str)
               Long/parseLong)))

(defn plus-n-mins
  [mins]
  (as-> (jt/local-date-time) h
      (.plusMinutes h mins)
      (jt/format "Hmm" h) 
      (Integer/parseInt h)))


(comment

   (fecha-actual)
  (hora-actual) 

  (plus-n-mins 40)
  )