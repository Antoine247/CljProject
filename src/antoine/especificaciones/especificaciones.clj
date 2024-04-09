(ns antoine.especificaciones.especificaciones
  (:require [clojure.spec.alpha :as spec] 
            [hyperfiddle.rcf :refer [tests]]))

(spec/def :seguridad/material-entregable (spec/int-in 1 3))
(spec/def :seguridad/alergia-anestesia (spec/int-in 1 3))


(tests
 (spec/valid? :seguridad/material-entregable 4) := false
 (spec/valid? :seguridad/material-entregable 10) := false
 (spec/valid? :seguridad/material-entregable -1) := false
 (spec/valid? :seguridad/material-entregable 2) := true
 (spec/valid? :seguridad/material-entregable 0) := false

 
 :rcf)
