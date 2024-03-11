(ns antoine.sql.habitacion-cama
  "queries encargadas de modificacion de las habitaciones y camas en desarrollo"
  (:require
   [antoine.servicios.conexiones :refer [consulta-maestros]]
   [honey.sql :as sql]))

(defn liberar-camas
  "libera la cama 1 de planta baja"
  []
  (let [query (sql/format {:update :tbc_HABITA
                           :set {:Hab_HistClin_2 0,
                                 :Hab_HistClin_1 0,
                                 :Hab_Estado 0}
                           :where [:= :Hab_NroHabi 1]})
        query2 (sql/format {:update :tbc_CAMAS
                            :set {:Cam_Est 0,
                                  :Cam_HistClin 0}
                            :where [:= :Cam_Habi 1]})]
    (doseq [q [query query2]] (consulta-maestros q))))

(liberar-camas)