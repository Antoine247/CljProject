(ns antoine.especificaciones.especificaciones
  (:require [clojure.spec.alpha :as spec] 
            [hyperfiddle.rcf :refer [tests]]))

(spec/def :seguridad/material-entregable (spec/int-in 1 3))
(spec/def :seguridad/alergia-anestesia (spec/int-in 1 3))
(spec/def :evaluacion-medica/presion-arterial (spec/int-in 20 300))
(spec/def :internado/es-parto (spec/or :some #{161 162 878}))

(tests
 (spec/valid? :seguridad/material-entregable 4) := false
 (spec/valid? :seguridad/material-entregable 10) := false
 (spec/valid? :seguridad/material-entregable -1) := false
 (spec/valid? :seguridad/material-entregable 2) := true
 (spec/valid? :seguridad/material-entregable 0) := false
 (spec/valid? :internado/es-parto 161) := true
 (spec/valid? :internado/es-parto 162) := true
 (spec/valid? :internado/es-parto 878) := true
 (spec/valid? :internado/es-parto 1) := false
 (spec/valid? :internado/es-parto 1201) := false
 (spec/valid? :internado/es-parto 0) := false
 (spec/valid? :internado/es-parto nil) := false
 :rcf) 
