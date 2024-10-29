(ns antoine.sql.anatomia-patologica
  (:require [antoine.servicios.conexiones :refer [consulta-asistencial]]
            [honey.sql :as sql]))

(defn insertar
  [{:keys [tbc_admision_scroll/Adm_HistClin
           tbc_guardia/Guar_HistClinica
           tbc_admision_scroll/Adm_FecIng
           tbc_guardia/Guar_FechaIngreso
           tbc_admision_scroll/Adm_HorIng
           tbc_guardia/Guar_HoraIngreso
           tbc_cirugint/ciriprotocolo
           tbc_ciruguar/cirgprotocolo] :as pac}]
  (let [historia-clinica (or Adm_HistClin Guar_HistClinica)
        fecha (or Adm_FecIng Guar_FechaIngreso)
        hora (or Adm_HorIng Guar_HoraIngreso)
        protocolo (or ciriprotocolo cirgprotocolo)
        insercion-cabecera (sql/format {:insert-into :tbc_anatpaca
                                        :columns [:apca_codmed
                                                  :apca_estado
                                                  :apca_fecingr
                                                  :apca_fecsolic_3
                                                  :apca_histclin
                                                  :apca_horingr
                                                  :apca_nrosolic
                                                  :apca_nrosolic_3
                                                  :apca_obrsoc
                                                  :apca_histnom
                                                  :apca_protocolo
                                                  :apca_servicio
                                                  :apca_tipadmi
                                                  :apca_tipmed
                                                  :apca_obsgral
                                                  :apca_lineas
                                                  :apca_tipmed
                                                  :apca_profinfor
                                                  :apca_matprofinfor
                                                  :apca_profinforcompl
                                                  :apca_matprofinforcompl
                                                  :apca_fecingr
                                                  :apca_fecreci
                                                  :apca_fecentre
                                                  :apca_feccargares
                                                  :apca_feccargacompl
                                                  :apca_obsgral
                                                  :apcapieza]
                                        :values []})
        insercion-detalle (sql/format {:insert-into :tbc_anatpade
                                       :columns [:apde_histclin
                                                 :apde_fecingr
                                                 :apde_fecsolic
                                                 :apde_horingr
                                                 :apde_nrosolic
                                                 :apde_nrosolic_3
                                                 :apde_codnom_3
                                                 :apde_protocolo
                                                 :apde_servicio
                                                 :apde_tipnom
                                                 :apde_tipnom_3]
                                       :values []})]
    pac))