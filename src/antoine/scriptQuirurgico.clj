(ns antoine.scriptQuirurgico
  (:require [antoine.system :refer [configuracion]] 
            [clojure.java.io :as io]
            [antoine.servicios.conexiones :as conn]
            [honey.sql :as sql]) 
  (:gen-class))

(defn greet
  "Callable entry point to the application."
  [data]
  (println (str "Hello, " (or (:name data) "World") "!")))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (greet {:name (first args)}))

(defn paciente-inicial
  "devuelve numero de historia clinica de paciente de ambulatorio sin protocolo ni seguridad quirurgica"
  []
  (let [query (sql/format {:select [:tbc_guardia.Guar_HistClinica :tbc_guardia.Guar_FechaIngreso, :tbc_guardia.Guar_HoraIngreso],
                           :from [[:tbc_guardia]]
                           :left-join[[:tbc_seguqui_new][:and
                                                              [:= :tbc_guardia.Guar_HistClinica :tbc_seguqui_new.SegHistClinica]
                                                              [:= :tbc_guardia.Guar_FechaIngreso :tbc_seguqui_new.SegFechaCarga]
                                                              [:= :tbc_guardia.Guar_HoraIngreso :tbc_seguqui_new.SegHoraCarga]]]
                           :where[:and
                                  [:= :tbc_guardia.Guar_Estado 1]
                                  [:is :tbc_seguqui_new.SegHistClinica :null]
                                  [:> :tbc_guardia.Guar_FechaIngreso 20220101]]})]
    (conn/ejecutar-enunciado configuracion :asistencial query)))

(let [query (sql/format {:select [:tbc_seguqui_new.SegFechaCarga, :tbc_seguqui_new.SegHoraCarga]
                         :from  [:tbc_seguqui_new]
                         :where [:= :tbc_seguqui_new.SegHistClinica 66843]})]
  (conn/ejecutar-enunciado configuracion :asistencial query))
