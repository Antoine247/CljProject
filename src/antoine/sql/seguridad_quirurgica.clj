(ns antoine.sql.seguridad-quirurgica
  "queries que se encargan de modificar la seguridad quirurgica ambulatoria"
  (:require [antoine.servicios.conexiones :refer [consulta-asistencial]]
            [antoine.especificaciones.generadores :refer [generar-seguridad-quirurgica]]
            [antoine.sql.numeradores :refer [obtener-nro-protocolo]]
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

(defn insertar
  "Recibe un mapa con HC y :tipo-solicitud (:completa-con-anestesia :completa-sin-anestesia :parcial-con-anestesia :parcial-sin-anestesia)
   Devuelve un mapa `paciente` tal como el input añadiéndole el nro de protocolo"
  [{:keys [tbc_admision_scroll/Adm_HistClin tbc_guardia/Guar_HistClinica tbc_guardia/Guar_HoraIngreso tbc_admision_scroll/Adm_HorIng tipo-solicitud] :as pac}] 
  (let [hc (or Adm_HistClin Guar_HistClinica)
        hora (or Adm_HorIng Guar_HoraIngreso)
        tipohc (if Adm_HistClin 0 1)
        protocolo (obtener-nro-protocolo)
        valores (case tipo-solicitud
                  :completa-con-anestesia (concat [hc hc tipohc tipohc hora protocolo] (generar-seguridad-quirurgica :completa-con-anestesia))
                  :completa-sin-anestesia (concat [hc hc tipohc tipohc hora protocolo] (generar-seguridad-quirurgica :completa-sin-anestesia))
                  :parcial-con-anestesia (concat [hc hc tipohc tipohc hora protocolo] (generar-seguridad-quirurgica :parcial-con-anestesia))
                  :parcial-sin-anestesia (concat [hc hc tipohc tipohc hora protocolo] (generar-seguridad-quirurgica :parcial-sin-anestesia))
                  (throw (IllegalArgumentException. "Opción inválida para generación de valores para seguridad quirúrgica")))
        stmt (sql/format {:insert-into :tbc_seguqui_new
                          :columns [:seghistclinica
                                    :seghistclinica1
                                    :segtipohc
                                    :segtipohc1
                                    :seghoracarga
                                    :segprotocolo
                                    :segfechacarga 
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
                          :values [valores]})]
    (when (consulta-asistencial stmt)
      (assoc pac :protocolo protocolo))))


(comment 
  (borrar #:tbc_guardia{:Guar_HistClinica 766499M, :Guar_FechaIngreso 20221004, :Guar_HoraIngreso 2050})
  (borrar #:tbc_admision_scroll{:Adm_HistClin 3192770M, :Adm_FecIng 20240308, :Adm_HorIng 1504}) 
  
  (def pac1 (assoc #:tbc_guardia {:Guar_HistClinica 509192M, :Guar_FechaIngreso 20240418, :Guar_HoraIngreso 1115} :tipo-solicitud :completa-con-anestesia))
  (def pac2 (assoc #:tbc_admision_scroll{:Adm_HistClin 3196850M, :Adm_FecIng 20240322, :Adm_HorIng 1830} :tipo-solicitud :completa-sin-anestesia))
  (def pac3 (assoc #:tbc_guardia{:Guar_HistClinica 736800M, :Guar_FechaIngreso 20240418, :Guar_HoraIngreso 1115} :tipo-solicitud :parcial-con-anestesia))
  (def pac4 (assoc #:tbc_admision_scroll{:Adm_HistClin 3201230M, :Adm_FecIng 20240408, :Adm_HorIng 1425} :tipo-solicitud :parcial-sin-anestesia))
  (insertar pac1)
  (insertar pac2)
  (insertar pac3) 
  (insertar pac4)
  
  )