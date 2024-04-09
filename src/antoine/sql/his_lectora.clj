(ns antoine.sql.his-lectora
  (:require [antoine.servicios.conexiones :refer [consulta-asistencial]]
            [honey.sql :as sql]
            [antoine.utils.utils :as utils]))

(defn borrar
  "Borro contenido en tbc_his_lectora dándole mapa de paciente con historia clinica y articulo a borrar."
  [{:keys [tbc_guardia/Guar_HistClinica tbc_guardia/Guar_FechaIngreso tbc_guardia/Guar_HoraIngreso 
           tbc_admision_scroll/Adm_HistClin tbc_admision_scroll/Adm_FecIng tbc_admision_scroll/Adm_HorIng]}] 
  (let [query (sql/format {:delete-from :tbc_his_lectora
                           :where [:and 
                                   [:= :tbc_his_lectora.Hlec_HistClin (or Guar_HistClinica Adm_HistClin)]
                                   [:= :tbc_his_lectora.Hlec_Fec (or Guar_FechaIngreso Adm_FecIng)]
                                   [:= :tbc_his_lectora.Hlec_Hora (or Guar_HoraIngreso Adm_HorIng)]]})]
    (consulta-asistencial query)))

(defn crear
  "crea una fila en his lectora, puede ser una operacion (Hlec_Art 9999) o medicamentos"
  [valores]
  {:pre [(or (utils/vector-doble? valores) (utils/vector-mapa? valores))]}
  (let [query (sql/format {:insert-into :tbc_his_lectora
                           :values valores})]
    (consulta-asistencial query)))

(defn actualizar
  "actualiza fila en his_lectora se debe enviar la historia clinica, el articulo a modificar y un mapa con :columna valor
   nota: :columna debe ser solo el nombre de la columna"
  [{:keys [tbc_guardia/Guar_HistClinica]} articulo mapa-valores]
  {:pre [(map? mapa-valores) (int? articulo)]}
  (let [query (sql/format {:update :tbc_his_lectora
                           :set mapa-valores
                           :where [:and
                                   [:= :tbc_his_lectora.Hlec_HistClin Guar_HistClinica]
                                   [:= :tbc_his_lectora.Hlec_Art articulo]]})]
    (consulta-asistencial query)))

