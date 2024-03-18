(ns antoine.sql.seguridad-quirurgica
  "queries que se encargan de modificar la seguridad quirurgica ambulatoria"
  (:require [antoine.servicios.conexiones :refer [consulta-asistencial]]
            [honey.sql :as sql]))

(defn borrar
  "Borra la seguridad quirurgica" 
  [{:keys [tbc_guardia/Guar_HistClinica tbc_guardia/Guar_FechaIngreso tbc_guardia/Guar_HoraIngreso
           tbc_admision_scroll/Adm_HistClin tbc_admision_scroll/Adm_FecIng tbc_admision_scroll/Adm_HorIng]}] 
  (let [query (sql/format {:delete-from :tbc_seguqui_new
                           :where [:and
                                   [:= :tbc_seguqui_new.SegHistClinica (or Guar_HistClinica Adm_HistClin)]
                                   [:= :tbc_seguqui_new.SegFechaCarga (or Guar_FechaIngreso Adm_FecIng)]
                                   [:= :tbc_seguqui_new.SegHoraCarga (or Guar_HoraIngreso Adm_HorIng)]]})]
    (consulta-asistencial query)))


(comment 
  (borrar #:tbc_guardia{:Guar_HistClinica 766499M, :Guar_FechaIngreso 20221004, :Guar_HoraIngreso 2050})
  (borrar #:tbc_admision_scroll{:Adm_HistClin 3192770M, :Adm_FecIng 20240308, :Adm_HorIng 1504}) 
  )