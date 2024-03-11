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



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; CON MULTIMETODOS ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defmulti ejecutar-enunciado
  "multifuncion que permite ejecutar una query a la base de asistencial y desal"
  {:arglists '([configuracion base-de-datos query & multiple-queries])}
  (fn [config base _ & _] (some #{base} (-> config :db keys))))

(defmethod ejecutar-enunciado :asistencial [conf base sentence & _]
  "Metodo cuando la base elegida es asistencial"
    (let  [asistencial (get (:db conf) base)]
    (try
      (with-open [conn (jdbc/get-connection asistencial)] 
        (jdbc/execute-one! conn sentence))
      (catch SQLException e (let [mensaje (.getMessage e)]
                              (u/log ::excepcion-en-consulta-relativity 
                                     :mensaje mensaje 
                                     :fecha (LocalDateTime/now)))))))

(defmethod ejecutar-enunciado :maestros [conf base sentence & _]
  "Metodo cuando la base elegida es maestros"
  (let  [maestros (get (:db conf) base)]
    (try
      (with-open [conn (jdbc/get-connection maestros)]
        (jdbc/execute-one! conn sentence))
      (catch SQLException e (let [mensaje (.getMessage e)]
                              (u/log ::excepcion-en-consulta-relativity
                                     :mensaje mensaje
                                     :fecha (LocalDateTime/now)))))))

(defmethod ejecutar-enunciado :asistencial [conf base _ & sentences]
  "ejecuta multiples queries en asistencial"
  {:arglists '([configuracion base-de-datos & queries])}
  (let  [asistencial (-> conf :db base)]
     (try
       (with-open [conn (jdbc/get-connection asistencial)]
         (doseq [sentence sentences]
           (jdbc/execute-one! conn sentence)))
       (catch SQLException e (let [mensaje (.getMessage e)]
                               (u/log ::excepcion-en-consulta-relativity
                                      :mensaje mensaje
                                      :fecha (LocalDateTime/now)))))))

(defmethod ejecutar-enunciado :desal [conf base sentence & _]
  "Metodo cuando la base elegida es desal"
  (let [desal (get (:db conf) base)]
    (try
      (with-open  [^HikariDataSource ds (connection/->pool HikariDataSource desal)]
           (jdbc/execute! ds sentence))
      (catch SQLException e (let [mensaje (.getMessage e)]
                              (u/log ::excepcion-en-consulta-postgres 
                                     :mensaje mensaje 
                                     :fecha (LocalDateTime/now)))))))

(defmethod ejecutar-enunciado :default [_ _ _ & _] 
  (u/log ::exception-en-consulta-default :mensaje "no se ingreso la base" :fecha (LocalDateTime/now)))

(comment
  (ns-unmap *ns* 'ejecutar-enunciado)

  (consulta-asistencial ["SELECT * FROM tbc_his_lectora WHERE hlec_protocolo = ?" 500351])

  (consulta-bases-auxiliares ["SELECT * FROM tbl_eventlog_cirugia"])
 
  (consulta-desal ["SELECT * FROM fichaaneste_cab LIMIT 10"])

  (consulta-maestros ["SELECT * FROM tbc_articulos"])

  :ref
  )

