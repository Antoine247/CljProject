(ns antoine.system
(:require [aero.core :refer [read-config]]
          [clojure.java.io :as io]))

(def rc (read-config (io/resource "config.edn")))

(comment 
  (defn consulta-relativity
    "Recibe un vector con la consulta y devuelve un vector de mapas con los resultados"
    [sentence]
    (try
      (with-open [conn (jdbc/get-connection {:dbtype (:rel-dbtype config)
                                             :classname (:rel-classname config)
                                             :dbname (:rel-dbname config)
                                             :user  (:rel-user config)
                                             :host (:rel-host config)
                                             :port (:rel-port config)})]
        (jdbc/execute! conn sentence))
      (catch SQLException e (let [mensaje (.getMessage e)]
                              (u/log ::excepcion-en-consulta-relativity :mensaje mensaje :fecha (LocalDateTime/now))
                              (prn (str "Lo sentimos, hubo una excepcion: " mensaje)))))))
