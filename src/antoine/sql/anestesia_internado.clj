(ns antoine.sql.anestesia-internado
  (:require [antoine.servicios.conexiones :refer [consulta-asistencial]]
            [antoine.especificaciones.generadores :refer [generar-evaluacion-anestesica-completa generar-evaluacion-preanestesica]]
            [honey.sql :as sql]
            [antoine.utils.utils :as utils]))

(defn borrar
  [{:keys [tbc_admision_scroll/Adm_HistClin]}]
  (let [query (sql/format {:delete-from :tbc_anestesia
                           :where [:= :tbc_anestesia.Anes_HistClin Adm_HistClin]})]
    (consulta-asistencial query)))

(defn insertar
  "`tipo-insercion`contempla las siguientes llaves => :preanestesica y :completa (pre y post-anestesica)"
  [{:keys [tbc_admision_scroll/Adm_HistClin tbc_admision_scroll/Adm_FecIng tbc_admision_scroll/Adm_HorIng tipo-insercion] :as pac}]
  (let [fn-generacion (case tipo-insercion
                        :preanestesica generar-evaluacion-preanestesica
                        :completa generar-evaluacion-anestesica-completa
                        (throw (IllegalArgumentException. "Opción no implementada")))
        valores (concat [Adm_HistClin Adm_FecIng Adm_HorIng] (fn-generacion))
        query (sql/format {:insert-into :tbc_anestesia
                           :columns [:anes_histclin
                                     :anes_feccarga
                                     :anes_horcarga
                                     :anes_estado
                                     :anes_numero
                                     :anes_protocolo
                                     :anes_codlegamed
                                     :anes_tiplegamed
                                     :anes_interven
                                     :anes_tipoint
                                     :anes_secreali
                                     :anes_presionmax
                                     :anes_presionmin
                                     :anes_pulsofrec
                                     :anes_pulsocarac
                                     :anes_asa
                                     :anes_tipocir
                                     :anes_escalam
                                     :anes_horini
                                     :anes_filler_1
                                     :anes_complipre_8
                                     :anes_complipre_7
                                     :anes_complipre_6
                                     :anes_complipre_5
                                     :anes_complipre_4
                                     :anes_complipre_3
                                     :anes_complipre_2
                                     :anes_complipre_1
                                     :anes_clinpre_33
                                     :anes_clinpre_32
                                     :anes_clinpre_31
                                     :anes_clinpre_30
                                     :anes_clinpre_29
                                     :anes_clinpre_28
                                     :anes_clinpre_27
                                     :anes_clinpre_26
                                     :anes_clinpre_25
                                     :anes_clinpre_24
                                     :anes_clinpre_23
                                     :anes_clinpre_22
                                     :anes_clinpre_21
                                     :anes_clinpre_20
                                     :anes_clinpre_19
                                     :anes_clinpre_18
                                     :anes_clinpre_17
                                     :anes_clinpre_16
                                     :anes_clinpre_15
                                     :anes_clinpre_14
                                     :anes_clinpre_13
                                     :anes_clinpre_12
                                     :anes_clinpre_11
                                     :anes_clinpre_10
                                     :anes_clinpre_9
                                     :anes_clinpre_8
                                     :anes_clipnre_7 ;; NO es error de tipeo, así está en la tabla
                                     :anes_clinpre_6
                                     :anes_clinpre_5
                                     :anes_clinpre_4
                                     :anes_clinpre_3
                                     :anes_clinpre_2
                                     :anes_clinpre_1
                                     :anes_tratprev_19
                                     :anes_tratprev_18
                                     :anes_tratprev_17
                                     :anes_tratprev_16
                                     :anes_tratprev_15
                                     :anes_tratprev_14
                                     :anes_tratprev_13
                                     :anes_tratprev_12
                                     :anes_tratprev_11
                                     :anes_tratprev_10
                                     :anes_tratprev_9
                                     :anes_tratprev_8
                                     :anes_tratprev_7
                                     :anes_tratprev_6
                                     :anes_tratprev_5
                                     :anes_tratprev_4
                                     :anes_tratprev_3
                                     :anes_tratprev_2
                                     :anes_tratprev_1
                                     :anes_tratpreobs
                                     :anes_cabcue_5
                                     :anes_cabcue_4
                                     :anes_cabcue_3
                                     :anes_cabcue_2
                                     :anes_cabcue_1
                                     :anes_anesregio_5
                                     :anes_anesregio_4
                                     :anes_anesregio_3
                                     :anes_anesregio_2
                                     :anes_anesregio_1
                                     :anes_anesregioobs
                                     :anes_exacompl_21
                                     :anes_exacompl_20
                                     :anes_exacompl_19
                                     :anes_exacompl_18
                                     :anes_exacompl_17
                                     :anes_exacompl_16
                                     :anes_exacompl_15
                                     :anes_exacompl_14
                                     :anes_exacompl_13
                                     :anes_exacompl_12
                                     :anes_exacompl_11
                                     :anes_exacompl_10
                                     :anes_exacompl_9
                                     :anes_exacom_8 ;; NO es error de tipeo, así está en la tabla
                                     :anes_exacompl_7
                                     :anes_exacompl_6
                                     :anes_exacompl_5
                                     :anes_exacompl_4
                                     :anes_exacompl_3
                                     :anes_exacompl_2
                                     :anes_exacompl_1
                                     :anes_exacomplobs
                                     :anes_psipre_6
                                     :anes_psipre_5
                                     :anes_psipre_4
                                     :anes_psipre_3
                                     :anes_psipre_2
                                     :anes_psipre_1
                                     :anes_exaesp_3
                                     :anes_exaesp_2
                                     :anes_exaesp_1
                                     :anes_exaespobs
                                     :anes_nrotxt
                                     :anes_condicion
                                     :anes_tipoblo
                                     :anes_cateter
                                     :anes_inhend
                                     :anes_intub
                                     :anes_manguito
                                     :anes_espasi
                                     :anes_mecman
                                     :anes_circuito
                                     :anes_cerrado
                                     :anes_apgar
                                     :anes_horrecup
                                     :anes_sector
                                     :anes_anesoper_9
                                     :anes_anesoper_8
                                     :anes_anesoper_7
                                     :anes_anesoper_6
                                     :anes_anesoper_5
                                     :anes_anesoper_4
                                     :anes_anesoper_3
                                     :anes_anesoper_2
                                     :anes_anesoper_1
                                     :anes_shock_3
                                     :anes_shock_2
                                     :anes_shock_1
                                     :anes_esca_5
                                     :anes_esca_4
                                     :anes_esca_3
                                     :anes_esca_2
                                     :anes_esca_1
                                     :anes_filler_3]
                           :values [valores]})]
    (when (consulta-asistencial query)
      pac)))

