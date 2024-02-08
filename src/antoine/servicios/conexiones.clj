(ns antoine.servicios.conexiones
  (:require 
            [next.jdbc :as jdbc]
            [next.jdbc.connection :as connection]
            [com.brunobonacci.mulog :as u])
  (:import (com.zaxxer.hikari HikariDataSource)
           (java.sql SQLException)
           (java.time LocalDateTime)))

(defmulti ejecutar-enunciado
  "multifuncion que recibe el archivo config.edn, la base a consultar en forma de keyword y la query a ejecutar"
  (fn [config base _] (some #{base} (-> config :db keys))))

(defmethod ejecutar-enunciado :asistencial [conf base sentence]
  "Metodo cuando la base elegida es asistencial"
    (let  [asistencial (get (:db conf) base)]
    (try
      (with-open [conn (jdbc/get-connection asistencial)] 
        (jdbc/execute-one! conn sentence))
      (catch SQLException e (let [mensaje (.getMessage e)]
                              (u/log ::excepcion-en-consulta-relativity 
                                     :mensaje mensaje 
                                     :fecha (LocalDateTime/now)))))))

(defmethod ejecutar-enunciado :desal [conf base sentence]
  "Metodo cuando la base elegida es desal"
  (let [desal (get (:db conf) base)]
    (try
      (with-open  [^HikariDataSource ds (connection/->pool HikariDataSource desal)]
           (jdbc/execute! ds sentence))
      (catch SQLException e (let [mensaje (.getMessage e)]
                              (u/log ::excepcion-en-consulta-postgres 
                                     :mensaje mensaje 
                                     :fecha (LocalDateTime/now)))))))

(defmethod ejecutar-enunciado :default [_ _ _] 
  (u/log ::exception-en-consulta-default :mensaje "no se ingreso la base" :fecha (LocalDateTime/now)))

