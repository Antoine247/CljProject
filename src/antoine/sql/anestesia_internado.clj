(ns antoine.sql.anestesia-internado
  (:require [antoine.servicios.conexiones :refer [consulta-asistencial]]
            [antoine.especificaciones.generador-anestesia :refer [generar-evaluacion-anestesica-completa generar-evaluacion-preanestesica]]
            [honey.sql :as sql]
            [antoine.utils.utils :as utils]))

(defn borrar
  [{:keys [tbc_admision_scroll/Adm_HistClin]}]
  (let [query (sql/format {:delete-from :tbc_anestesia
                           :where [:= :tbc_anestesia.Anes_HistClin Adm_HistClin]})]
    (consulta-asistencial query)))

(defn insertar
  "`tipo-insercion`contempla las siguientes llaves => :preanestesica y :completa (pre y post-anestesica)"
  [{:keys [tbc_admision_scroll/Adm_HistClin tbc_admision_scroll/Adm_FecIng tbc_admision_scroll/Adm_HorIng protocolo tipo-insercion] :as pac}]
  (let [fn-generacion (case tipo-insercion
                        :preanestesica generar-evaluacion-preanestesica
                        :completa generar-evaluacion-anestesica-completa
                        (throw (IllegalArgumentException. "Opción no implementada")))
        valores (concat [Adm_HistClin Adm_FecIng Adm_HorIng protocolo] (fn-generacion))
        query (sql/format {:insert-into :tbc_anestesia
                           :columns [:anes_histclin
                                     :anes_feccarga
                                     :anes_horcarga
                                     :anes_protocolo
                                     :anes_estado
                                     :anes_numero 
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



(comment
  
  (def columnas [:anes_histclin
                 :anes_feccarga
                 :anes_horcarga
                 :anes_protocolo
                 :anes_estado
                 :anes_numero
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
                 :anes_filler_3])
  (count columnas)
  (def campos '(Anes_HistClin
                Anes_FecCarga
                Anes_HorCarga
                Anes_Estado
                Anes_Numero
                Anes_Protocolo
                Anes_CodLegaMed
                Anes_TipLegaMed
                Anes_Interven
                Anes_TipoInt
                Anes_SecReali
                Anes_PresionMax
                Anes_PresionMin
                Anes_PulsoFrec
                Anes_PulsoCarac
                Anes_Asa
                Anes_TipoCir
                Anes_EscalaM
                Anes_HorIni
                Anes_Filler_1
                Anes_CompliPre_8
                Anes_CompliPre_7
                Anes_CompliPre_6
                Anes_CompliPre_5
                Anes_CompliPre_4
                Anes_CompliPre_3
                Anes_CompliPre_2
                Anes_CompliPre_1
                Anes_ClinPre_33
                Anes_ClinPre_32
                Anes_ClinPre_31
                Anes_ClinPre_30
                Anes_ClinPre_29
                Anes_ClinPre_28
                Anes_ClinPre_27
                Anes_ClinPre_26
                Anes_ClinPre_25
                Anes_ClinPre_24
                Anes_ClinPre_23
                Anes_ClinPre_22
                Anes_ClinPre_21
                Anes_ClinPre_20
                Anes_ClinPre_19
                Anes_ClinPre_18
                Anes_ClinPre_17
                Anes_ClinPre_16
                Anes_ClinPre_15
                Anes_ClinPre_14
                Anes_ClinPre_13
                Anes_ClinPre_12
                Anes_ClinPre_11
                Anes_ClinPre_10
                Anes_ClinPre_9
                Anes_ClinPre_8
                Anes_CliPnre_7
                Anes_ClinPre_6
                Anes_ClinPre_5
                Anes_ClinPre_4
                Anes_ClinPre_3
                Anes_ClinPre_2
                Anes_ClinPre_1
                Anes_TratPrev_19
                Anes_TratPrev_18
                Anes_TratPrev_17
                Anes_TratPrev_16
                Anes_TratPrev_15
                Anes_TratPrev_14
                Anes_TratPrev_13
                Anes_TratPrev_12
                Anes_TratPrev_11
                Anes_TratPrev_10
                Anes_TratPrev_9
                Anes_TratPrev_8
                Anes_TratPrev_7
                Anes_TratPrev_6
                Anes_TratPrev_5
                Anes_TratPrev_4
                Anes_TratPrev_3
                Anes_TratPrev_2
                Anes_TratPrev_1
                Anes_TratPreObs
                Anes_CabCue_5
                Anes_CabCue_4
                Anes_CabCue_3
                Anes_CabCue_2
                Anes_CabCue_1
                Anes_AnesRegio_5
                Anes_AnesRegio_4
                Anes_AnesRegio_3
                Anes_AnesRegio_2
                Anes_AnesRegio_1
                Anes_AnesRegioObs
                Anes_ExaCompl_21
                Anes_ExaCompl_20
                Anes_ExaCompl_19
                Anes_ExaCompl_18
                Anes_ExaCompl_17
                Anes_ExaCompl_16
                Anes_ExaCompl_15
                Anes_ExaCompl_14
                Anes_ExaCompl_13
                Anes_ExaCompl_12
                Anes_ExaCompl_11
                Anes_ExaCompl_10
                Anes_ExaCompl_9
                Anes_ExaCom_8
                Anes_ExaCompl_7
                Anes_ExaCompl_6
                Anes_ExaCompl_5
                Anes_ExaCompl_4
                Anes_ExaCompl_3
                Anes_ExaCompl_2
                Anes_ExaCompl_1
                Anes_ExaComplObs
                Anes_PsiPre_6
                Anes_PsiPre_5
                Anes_PsiPre_4
                Anes_PsiPre_3
                Anes_PsiPre_2
                Anes_PsiPre_1
                Anes_ExaEsp_3
                Anes_ExaEsp_2
                Anes_ExaEsp_1
                Anes_ExaEspObs
                Anes_NroTxt
                Anes_Condicion
                Anes_Tipoblo
                Anes_Cateter
                Anes_InhEnd
                Anes_Intub
                Anes_Manguito
                Anes_EspAsi
                Anes_MecMan
                Anes_Circuito
                Anes_Cerrado
                Anes_Apgar
                Anes_HorRecup
                Anes_Sector
                Anes_AnesOper_9
                Anes_AnesOper_8
                Anes_AnesOper_7
                Anes_AnesOper_6
                Anes_AnesOper_5
                Anes_AnesOper_4
                Anes_AnesOper_3
                Anes_AnesOper_2
                Anes_AnesOper_1
                Anes_Shock_3
                Anes_Shock_2
                Anes_Shock_1
                Anes_EscA_5
                Anes_EscA_4
                Anes_EscA_3
                Anes_EscA_2
                Anes_EscA_1
                Anes_Filler_3))
  (count campos)
  )