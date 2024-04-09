(ns antoine.sql.seguridad-quirurgica
  "queries que se encargan de modificar la seguridad quirurgica ambulatoria"
  (:require [antoine.servicios.conexiones :refer [consulta-asistencial]]
            [antoine.especificaciones.generadores :refer [generar-seguridad-quirugica-parcial generar-seguridad-quirurgica-completa]]
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
;; Creo que habría que agregar completa-con-anestesia y completa-sin-anestesica
(defn insertar
  [{:keys [tbc_admision_scroll/Adm_HistClin tbc_guardia/Guar_HistClinica tipo-solicitud]}] ;; tipo-solicitud tendrá como valor :completa o :parcial en indicará el tipo de inserción
  (let [hc (or Adm_HistClin Guar_HistClinica)
        tipohc (if Adm_HistClin 0 1)
        valores (case tipo-solicitud
                  :completa (concat [hc hc tipohc tipohc] (generar-seguridad-quirurgica-completa))
                  :parcial (concat [hc hc tipohc tipohc] (generar-seguridad-quirugica-parcial))
                  (throw (IllegalArgumentException. "Opción inválida para generación de valores para seguridad quirúrgica")))
        stmt (case tipo-solicitud
               :completa (sql/format {:insert-into :tbc_seguqui_new
                                      :columns [:seghistclinica
                                                :seghistclinica1
                                                :segtipohc
                                                :segtipohc1
                                                :segfechacarga
                                                :seghoracarga
                                                :segprotocolo
                                                :segcirculmate
                                                :segtipoadmin
                                                :seglegaadmin
                                                :segtipocirculini
                                                :seglegacirculini
                                                :seganestalergia
                                                :segestado
                                                :segadminident
                                                :segadmindiag
                                                :segadminconsen
                                                :segcirculident
                                                :segcirculbanio
                                                :segcirculhiscli
                                                :segcirculestu
                                                :segcirculprote
                                                :segcirculconsen
                                                :seganestident
                                                :seganestpulso
                                                :seganestseguri
                                                :seganestcuales
                                                :seganestviaaerea
                                                :seganestacceso
                                                :seganestsangre
                                                :seglegaanestini
                                                :segtipoanestini
                                                :segciruident
                                                :segciruantibiot
                                                :segcirubisturi
                                                :segciruincidentes
                                                :seglegaciruini
                                                :segtipociruini
                                                :seganestrepasaini
                                                :seglegaanestrepi
                                                :segtipoanestrepi
                                                :seginstrurepasaini
                                                :seginstrugasasini
                                                :seginstrupinzasini
                                                :seglegainstruini
                                                :segtipoinstruini
                                                :segciruproced
                                                :segciruindica
                                                :seglegacirufin
                                                :segtipocirufin
                                                :seganestplan
                                                :seglegaanestfin
                                                :segtipoanestfin
                                                :seginstrugasasfin
                                                :seginstrupinzasfin
                                                :seglegainstrufin
                                                :segtipoinstrufin
                                                :segcirculmuestras
                                                :segcirculregistro
                                                :seglegacirculfin
                                                :segtipocirculfin
                                                :seganestocular
                                                :seganestdecubi
                                                :seganestcomorb
                                                :segcirculproinstru
                                                :segcirculnormot
                                                :segcirculparteciru
                                                :segcirculparteanes
                                                :segcirculequipres
                                                :segcirculequifunc
                                                :segcirculidsipr
                                                :segcirculdecubi
                                                :segcirculimprev
                                                :segcirculanesproblema
                                                :seglegacirculcut
                                                :segtipocirculcut]
                                      :values [valores]})
               :parcial (sql/format {:insert-into :tbc_seguqui_new
                                     :columns [:seghistclinica
                                               :seghistclinica1
                                               :segtipohc
                                               :segtipohc1
                                               :segfechacarga
                                               :seghoracarga
                                               :segprotocolo
                                               :segcirculmate
                                               :segtipoadmin
                                               :seglegaadmin
                                               :segtipocirculini
                                               :seglegacirculini
                                               :seganestalergia]
                                     :values [valores]})
               (throw (IllegalArgumentException. "Opción inválida para inserción de registro en seguridad quirúrgica")))]
    (consulta-asistencial stmt)))


(comment 
  (borrar #:tbc_guardia{:Guar_HistClinica 766499M, :Guar_FechaIngreso 20221004, :Guar_HoraIngreso 2050})
  (borrar #:tbc_admision_scroll{:Adm_HistClin 3192770M, :Adm_FecIng 20240308, :Adm_HorIng 1504}) 
 
  (def pac (assoc #:tbc_admision_scroll{:Adm_HistClin 3194150M, :Adm_FecIng 20240313, :Adm_HorIng 2201} :tipo-solicitud :completa))
  (insertar pac) 
  )