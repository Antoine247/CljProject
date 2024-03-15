(ns antoine.sanatorio
  (:require [cli-matic.core :refer [run-cmd]]
            [cli-matic.utils :as U]
            [antoine.comandos :refer [crear_protocolo_ambulatorio crear_protocolo_internado]])
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
                         {:as "Hemodinamia"
                          :option "hem"
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
                         {:as "Anatomía Patológica"
                          :option "anatpa"
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
                          :type :flag}
                         {:as "Parto"
                          :option "prto"
                          :type :flag}]
                  :runs crear_protocolo_internado}
                 {:command "protocolo_ambulatorio"
                  :description "Crea un protocolo ambulatorio completo o bien con los parámetros indicados por las opciones"
                  :opts [{:as "Alerta asistencial"
                          :option "aler"
                          :type :flag}
                         {:as "Completo con anestesia"
                          :option "ca"
                          :type :flag}
                         {:as "Hemodinamia"
                          :option "hem"
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
                         {:as "Historia Clínica de Ingreso"
                          :option "hci"
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
                  :runs crear_protocolo_ambulatorio}]})

(defn -main 
  [& args]
  (run-cmd args CONFIGURATION))


(comment
  
(let [ar (list "-ca" "2" "-sqc" "ald")] 
  (run-cmd ar CONFIGURATION))

  :ref
  )
