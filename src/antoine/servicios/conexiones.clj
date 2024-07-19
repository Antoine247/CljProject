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
  [k all-rows?]
  (let [ds (obtener-datasource :relativity k)] 
    (if all-rows?
      (fn consulta [sentencia]
        (try
          (with-open [conn (jdbc/get-connection ds)] 
            (jdbc/execute! conn sentencia))
          (catch SQLException e (let [mensaje (.getMessage e)]
                                  (u/log ::excepcion-en-consulta-relativity
                                         :sentencia sentencia
                                         :mensaje mensaje
                                         :fecha (LocalDateTime/now))))))
      (fn consulta [sentencia]
        (try
          (with-open [conn (jdbc/get-connection ds)]
            (jdbc/execute-one! conn sentencia))
          (catch SQLException e (let [mensaje (.getMessage e)]
                                  (u/log ::excepcion-en-consulta-relativity
                                         :sentencia sentencia
                                         :mensaje mensaje
                                         :fecha (LocalDateTime/now)))))))))

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
                                       :sentencia sentencia
                                       :mensaje mensaje
                                       :fecha (LocalDateTime/now))))))))
  
(defmacro con-transaccion-postgres
  "Recibe una llave indicando la base postgres en la que crear la transacción y uno o más vectores de operaciones SQL que ejecutar en la transacción.
   Las operaciones deben ser independientes"
  [k & cuerpo]
  (let [tx-sym (gensym "tx")]
    `(jdbc/with-transaction [~tx-sym (obtener-datasource :postgres ~k)]
       ~@(for [exp cuerpo]
           `(jdbc/execute! ~tx-sym ~exp)))))
 
(def consulta-desal (obtener-conexion-postgres :desal))

(def consulta-bases-auxiliares (obtener-conexion-postgres :bases_auxiliares))

(def consulta-asistencial (obtener-conexion-relativity :asistencial false))

(def consulta-asistencial-todo (obtener-conexion-relativity :asistencial true))

(def consulta-maestros (obtener-conexion-relativity :maestros false))

(def consulta-maestros-todo (obtener-conexion-relativity :maestros true))

(def consulta-parametros (obtener-conexion-postgres :parametros))

;(def transaccion-parametros (fn [& args] (apply #(con-transaccion-postgres :parametros %) args)))


(comment
  (require '[honey.sql :as sql])

  (def r (consulta-parametros (sql/format {:select :prm_54
                                           :from [:param]
                                           :where [:= :key_param 1]})))
  (:param/prm_54 (first r))
 
  (transaccion-parametros 
                          (sql/format {:update :param
                                       :set {:prm_54 (inc (:param/prm_54 (first r)))}
                                       :where [:= :key-param 1]}))
  
(jdbc/with-transaction [tx (obtener-datasource :postgres :parametros)]
  (let [p (jdbc/execute! tx (sql/format {:select :prm_54
                                        :from [:param]
                                        :where [:= :key_param 1]}))]
    (jdbc/execute! tx (sql/format {:update :param
                                :set {:prm_54 (inc (:param/prm_54 (first p)))}
                                :where [:= :key-param 1]}))))
   
  (ns-unmap *ns* 'ejecutar-enunciado)

  (consulta-asistencial ["SELECT * FROM tbc_his_lectora WHERE hlec_protocolo = 109862"])

  (consulta-asistencial ["SELECT * FROM tbc_admision_scroll"])

  (consulta-bases-auxiliares ["SELECT * FROM tbl_eventlog_cirugia"])

  (consulta-desal ["SELECT * FROM fichaaneste_cab LIMIT 10"])

  (consulta-maestros ["SELECT * FROM tbc_articulos"])

  (consulta-maestros ["SELECT * FROM tbc_analisis"])

  (consulta-parametros ["SELECT * FROM param"])

  (macroexpand-1 '(con-transaccion-postgres :bases_auxiliares ["INSERT INTO tbl_eventlog_uti (historia_clinica, evento) 
                                                       VALUES(1000, 'PRUEBA_TRANSACCION')"]
                                            ["INSERT INTO tbl_eventlog_uco (historia_clinica, evento) 
                                           VALUES(2000, 'PRUEBA_TRANSACCION')"]))




  :ref
  )

