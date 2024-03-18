(ns antoine.sql.paciente-ambulatorio
  "scripts que se encargan de generar un paciente de ambulatorio limpio"
  (:require [antoine.servicios.conexiones :refer [consulta-asistencial]] 
            [honey.sql :as sql]
            [antoine.utils.utils :as utils]))

(defn limpiar-paciente
  "Modifica la historia clínica o una random para que esté en su version inicial"
  [{:keys [tbc_guardia/Guar_HistClinica tbc_guardia/Guar_FechaIngreso tbc_guardia/Guar_HoraIngreso]}
   mapa-valores]
  {:pre [(map? mapa-valores)]}
  (let [query (sql/format {:update :tbc_guardia
                           :set mapa-valores
                           :where [:and [:= :tbc_guardia.Guar_HistClinica Guar_HistClinica]
                                   [:= :tbc_guardia.Guar_FechaIngreso Guar_FechaIngreso]
                                   [:= :tbc_guardia.Guar_HoraIngreso Guar_HoraIngreso]]})]
    (consulta-asistencial query)))

(defn paciente-ambulatorio-aleatorio
  "genera un numero random entre 500.000 y 800.000 y de ahi obtiene una historia, 
   si se le pasa la historia clinica va a usar esa"
  ([] (paciente-ambulatorio-aleatorio nil))
  ([histcli]
   {:pre [(or (int? histcli) (nil? histcli))]}
   (let [hc (if (nil? histcli) (utils/histclirand) histcli)
         query (sql/format {:select [:tbc_guardia.Guar_HistClinica
                                     :tbc_guardia.Guar_FechaIngreso
                                     :tbc_guardia.Guar_HoraIngreso]
                            :from [:tbc_guardia]
                            :where [:= :tbc_guardia.Guar_HistClinica hc]})
         q (consulta-asistencial query)]
     (if (map? q) 
       q
       (recur nil)))))

 
(comment
  (paciente-ambulatorio-aleatorio)

  (limpiar-paciente (paciente-ambulatorio-aleatorio 758036) {:Guar_Estado 1,
                                                             :Guar_Estado1 1,
                                                             :Guar_Estado3 1
                                                             :Guar_Medico 0,
                                                             :Guar_TipoMed 0,
                                                             :Guar_FechaAlta 0,
                                                             :Guar_HoraAlta 0,
                                                             :Guar_EspMed 0,
                                                             :Guar_HoraAtenc 0})

  (let [hc (utils/histclirand)
        query (sql/format {:select [:tbc_guardia.Guar_HistClinica
                                    :tbc_guardia.Guar_FechaIngreso
                                    :tbc_guardia.Guar_HoraIngreso]
                           :from [:tbc_guardia]
                           :where [:= :tbc_guardia.Guar_HistClinica hc]})]
    (println hc)
    (consulta-asistencial query))
  
  (let [query (sql/format {:select [:tbc_guardia.Guar_HistClinica
                                    :tbc_guardia.Guar_FechaIngreso
                                    :tbc_guardia.Guar_HoraIngreso]
                           :from [:tbc_guardia]})] 
    (consulta-asistencial query))
  
  (let [query (sql/format {:update :tbc_guardia
                           :set {:Guar_Estado 1,
                                 :Guar_Estado1 1,
                                 :Guar_Estado3 1
                                 :Guar_Medico 0,
                                 :Guar_TipoMed 0,
                                 :Guar_FechaAlta 0,
                                 :Guar_HoraAlta 0,
                                 :Guar_EspMed 0,
                                 :Guar_HoraAtenc 0
                                 :Guar_FechaIngreso (utils/fecha-actual)
                                 :Guar_HoraIngreso (utils/hora-actual)}
                           :where [:and [:= :tbc_guardia.Guar_HistClinica 551540]
                                   [:= :tbc_guardia.Guar_FechaIngreso 20160722]
                                   [:= :tbc_guardia.Guar_HoraIngreso 1632]]})]
    query
    #_(consulta-asistencial query))
  
  (concat '([:tbc_guardia/Guar_HistClinica 558960M] [:tbc_guardia/Guar_FechaIngreso 20161107] [:tbc_guardia/Guar_HoraIngreso 1542]))
  :ref)

