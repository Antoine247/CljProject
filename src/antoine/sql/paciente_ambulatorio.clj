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
                            :where [:= :tbc_guardia.Guar_HistClinica hc]})]
     (if-let [q (consulta-asistencial query)]
       q
       (recur nil)))))

(defn reinicio-paciente
  "llama a todas las funciones necesarias para borrar todos los registros de un paciente y poder usarlo desde 0
   puedes llamar la funcion sola y te va a usar un paciente random o puedes darle la historia clinica"
  [histcli]
  (let [paciente (paciente/limpiar-paciente (paciente/paciente-ambulatorio-aleatorio histcli) {:Guar_Estado 1,
                                                                                               :Guar_Estado1 1,
                                                                                               :Guar_Estado3 1
                                                                                               :Guar_Medico 0,
                                                                                               :Guar_TipoMed 0,
                                                                                               :Guar_FechaAlta 0,
                                                                                               :Guar_HoraAlta 0,
                                                                                               :Guar_EspMed 0,
                                                                                               :Guar_HoraAtenc 0})]
    (seguridad/borrar paciente)
    (alerta/borrar paciente)
    (lectora/borrar paciente 9999)
    (anes-ambu/borrar paciente)
    paciente))

(comment
  (limpiar-paciente (paciente-ambulatorio-aleatorio 758036) {:Guar_Estado 1,
                                                             :Guar_Estado1 1,
                                                             :Guar_Estado3 1
                                                             :Guar_Medico 0,
                                                             :Guar_TipoMed 0,
                                                             :Guar_FechaAlta 0,
                                                             :Guar_HoraAlta 0,
                                                             :Guar_EspMed 0,
                                                             :Guar_HoraAtenc 0})
  :ref)




(comment
(+ 2 2)
  (paciente-ambulatorio-aleatorio 758036)
  (def prueba
    (paciente-ambulatorio-aleatorio))

  (get prueba :tbc_guardia/Guar_HistClinica)

  (let [{:keys [tbc_guardia/Guar_HistClinica tbc_guardia/Guar_FechaIngreso]} prueba]
    [Guar_HistClinica Guar_FechaIngreso])
  :ref)

(comment

  (let [query (sql/format {:delete-from [:tbc_seguqui_new]
                           :where [:= :tbc_seguqui_new.SegHistClinica 554227]})
        query2 (sql/format {:delete-from [:tbc_anes_ambu]
                            :where [:= :tbc_anes_ambu.AnesHc 554227]})]
    #_(with-open  [conn (jdbc/get-connection (get-in  configuracion [:db :asistencial]))
                   stmt (jdbc/prepare conn (concat query query2))]
        stmt)
    (conn/ejecutar-enunciado configuracion :asistencial nil query query2))

  (with-open  [conn (jdbc/get-connection (get-in  configuracion [:db :asistencial]))
               stmt (jdbc/prepare conn [["DELETE FROM tbc_seguqui_new WHERE tbc_seguqui_new.SegHistClinica = ?" "DELETE FROM tbc_anes_ambu WHERE tbc_anes_ambu.AnesHc = ?"]
                                        [554227]])]
    stmt)

  (let [query (sql/format {:select [:*]
                           :from [:tbc_seguqui_new]
                           :where [:= :tbc_seguqui_new.SegHistClinica 554227]})]
    (conn/ejecutar-enunciado configuracion :asistencial query))

  ()
  :ref)