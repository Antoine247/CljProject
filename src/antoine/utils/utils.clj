(ns antoine.utils.utils)

(defn vector-doble?
  [xs]
  (and (vector? xs) (every? vector? xs)))

(defn vector-mapa?
  [xs]
  (and (vector? xs) (every? map? xs)))