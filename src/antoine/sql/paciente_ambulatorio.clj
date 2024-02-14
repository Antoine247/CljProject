(ns antoine.sql.paciente_ambulatorio
  "scripts que se encargan de generar un paciente de ambulatorio limpio"
  (:require [antoine.system :refer [configuracion]]
            [antoine.servicios.conexiones :as conn]
            [honey.sql :as sql]))

(defn histclirand
  "genera un numero random entre 500.000 y 800.000"
  []
  (+ 500000 (rand-int 300000)))

(defn paciente-ambulatorio-aleatorio
  "genera un numero random entre 500.000 y 800.000 y de ahi obtiene una historia, 
   si se le pasa la historia clinica va a usar esa" 
  ([](paciente-ambulatorio-aleatorio nil))
  ([histcli]
   (let [query (sql/format {:select [:tbc_guardia.Guar_HistClinica
                                     :tbc_guardia.Guar_FechaIngreso
                                     :tbc_guardia.Guar_HoraIngreso]
                            :from [:tbc_guardia]
                            :where [:= :tbc_guardia.Guar_HistClinica (if (nil? histcli) (histclirand) histcli)]})]
     (if-let [q (conn/ejecutar-enunciado configuracion :asistencial query)]
       q
       (recur nil)))))

(paciente-ambulatorio-aleatorio 758036)





(comment
  (def prueba
    (paciente-ambulatorio-aleatorio))

  (get prueba :tbc_guardia/Guar_HistClinica)

  (let [{:keys [tbc_guardia/Guar_HistClinica tbc_guardia/Guar_FechaIngreso]} prueba]
    [Guar_HistClinica Guar_FechaIngreso])
  :ref)



(defn obtiene-historia
  "toma una historia de acuer")
[histcli]
()

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