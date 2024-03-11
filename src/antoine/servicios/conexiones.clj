(ns antoine.servicios.conexiones
  "Conexion a la base de datos y acceso a los metodos ejecutar-enunciado"
  (:require [next.jdbc :as jdbc]
            [next.jdbc.connection :as connection]
            [com.brunobonacci.mulog :as u]
            [antoine.system :refer [obtener-datasource]])
  (:import (com.zaxxer.hikari HikariDataSource)
           (java.sql SQLException)
           (java.time LocalDateTime)))

(defn- obtener-conexion-relativity
  "Recibe una llave k que refiere a una base de relativity y devuelve una función que realiza consultas a esa base"
  [k]
  (let [ds (obtener-datasource :relativity k)]
    (fn consulta [sentencia]
      (try
        (with-open [conn (jdbc/get-connection ds)]
          (jdbc/execute-one! conn sentencia))
        (catch SQLException e (let [mensaje (.getMessage e)]
                                (u/log ::excepcion-en-consulta-relativity
                                       :mensaje mensaje
                                       :fecha (LocalDateTime/now))))))))

(defn- obtener-conexion-postgres 
  "Recibe una llave k que refiere a una base de postgres y devuelve una función que realiza consultas a esa base"
  [k]
  (let [ds (obtener-datasource :postgres k)]
    (fn consulta [sentencia]
      (try
        (with-open  [^HikariDataSource ds (connection/->pool HikariDataSource ds)]
          (jdbc/execute! ds sentencia))
        (catch SQLException e (let [mensaje (.getMessage e)]
                                (u/log ::excepcion-en-consulta-postgres
                                       :mensaje mensaje
                                       :fecha (LocalDateTime/now))))))))

(def consulta-desal (obtener-conexion-postgres :desal))

(def consulta-bases-auxiliares (obtener-conexion-postgres :bases_auxiliares))

(def consulta-asistencial (obtener-conexion-relativity :asistencial))

(def consulta-maestros (obtener-conexion-relativity :maestros))


(comment
  (ns-unmap *ns* 'ejecutar-enunciado)

  (consulta-asistencial ["SELECT * FROM tbc_his_lectora WHERE hlec_protocolo = ?" 500351])

  (consulta-bases-auxiliares ["SELECT * FROM tbl_eventlog_cirugia"])
 
  (consulta-desal ["SELECT * FROM fichaaneste_cab LIMIT 10"])

  (consulta-maestros ["SELECT * FROM tbc_articulos"])

  :ref
  )

