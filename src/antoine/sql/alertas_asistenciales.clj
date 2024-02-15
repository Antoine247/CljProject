(ns antoine.sql.alertas_asistenciales
  "queries que se encargan de modificar las alertas asistenciales"
  (:require [antoine.system :refer [configuracion]]
            [antoine.servicios.conexiones :as conn]
            [honey.sql :as sql]
            [antoine.utils.utils :as utils]))

(defn borrar
  "borra las alertas asistenciales asignadas a la historia clinica"
  [{:keys [tbc_guardia/Guar_HistClinica]}]
  (let [query (sql/format {:delete-from :tbc_alerta
                           :where [:= :tbc_alerta.Aler_HistClin Guar_HistClinica]})]
    (conn/ejecutar-enunciado configuracion :asistencial query)))

(defn crear
  "crea una alerta asistencial"
  [valores]
  {:pre [(or (utils/vector-doble? valores) (utils/vector-mapa? valores))]}
  (let [query (sql/format {:insert-into :tbc_alerta
                           :values valores})]
    (conn/ejecutar-enunciado configuracion :asistencial query)))

(defn actualizar
  "actualiza alerta asistencial se debe enviar la historia clinica y un mapa con :columna valor
   nota: :columna debe ser solo el nombre de la columna"
  [histcli mapa-valores]
  {:pre [(map? mapa-valores) (int? histcli)]}
  (let [query (sql/format {:update :tbc_alerta
                           :set mapa-valores
                           :where [:= :tbc_alerta.Aler_HistClin histcli]})]
    (conn/ejecutar-enunciado configuracion :asistencial query)))


(comment
  (def paciente)
  (borrar-alerta 758036)
  
  (type (sql/format {:delete-from :tbc_alerta
                     :where [:= :tbc_alerta.Aler_HistClin 1]}))
  (defn validacion-vectores
    [vector]
    {:pre [(vector? vector) (every? vector? vector)]}
    vector)
  (crear-alerta [1])


  (crear-alerta [[1 758036 0 7605 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 "" ""]])

  (actualizar-alerta 758036 {:Aler_TipAler_4 1})
  (let [v [1]]
    (and (vector? v) (every? vector? v)))

  :ref
  )


