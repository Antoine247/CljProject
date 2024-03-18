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


(comment

   (fecha-actual)
  (hora-actual) 
  )