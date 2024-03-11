(ns antoine.scriptQuirurgico
  (:require [cli-matic.core :refer [run-cmd]]
            [cli-matic.utils :as U])
  (:gen-class))

(def CONFIGURATION
  {:command "crea"
   :description "Programa para introducir datos de prueba en aplicaciones Servoy en servicio para el Sanatorio Colegiales"
   :version "0.0.1"
   :opts [{:as "pacientes"
           :default 1
           :option "p"
           :type :int}]
   :subcommands [{:command "protocolo_internado"
                  :description "Crea un protocolo internado completo o bien con los parámetros indicados por las opciones"
                  :opts [{:as "Completo con anestesia"
                          :option "ca"
                          :type :flag}
                         {:as "Completo sin anestesia"
                          :option "csa"
                          :type :flag}
                         {:as "Seguridad Quirurgica Completa"
                          :option "sqc"
                          :type :flag}
                         {:as "Seguridad Quirurgica antes incision"
                          :option "sqi"
                          :type :flag}
                         {:as "Evaluacion anestesica preoperatoria"
                          :option "eap"
                          :type :flag}
                         {:as "Ficha anestesica"
                          :option "fa"
                          :type :flag}
                         {:as "Ficha anestesica sin cerrar"
                          :option "fasc"
                          :type :flag}
                         {:as "Ficha anestesica sin medicamentos"
                          :option "fasm"
                          :type :flag}
                         {:as "Medicamentos cirugia"
                          :option "m"
                          :type :flag}
                         {:as "Con Evaluacion Post Anestesica"
                          :option "cepa"
                          :type :flag}
                         {:as "Sin Evaluacion Post Anestesica"
                          :option "sepa"
                          :type :flag}
                         {:as "Retiro de implante"
                          :option "rim"
                          :type :flag}
                         {:as "Insercion de implante"
                          :option "imp"
                          :type :flag}]
                  :runs prn}
                 {:command "protocolo_ambulatorio"
                  :description "Crea un protocolo ambulatorio completo o bien con los parámetros indicados por las opciones"
                  :opts []
                  :runs prn}]})

(defn -main 
  [& args]
  (run-cmd args CONFIGURATION))


(comment
  
(let [ar (list "-ca" "2" "-sqc" "ald")] 
  (run-cmd ar CONFIGURATION))

  :ref
  )
