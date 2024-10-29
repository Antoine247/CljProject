(ns antoine.especificaciones.generador-anestesia
  (:require [antoine.utils.utils :refer [fecha-actual hora-actual plus-n-mins]]
            [antoine.especificaciones.generadores-utils :refer [generar-nombre
                                                                legajos-personal
                                                                legajo-anestesiologo 
                                                                legajo-medico
                                                                legajo-sin-digito-verificador
                                                                generar-intervencion
                                                                generar-presiones-arteriales
                                                                generar-pulso
                                                                presion-min-max]]
            [clojure.spec.gen.alpha :as gen]
            [clojure.spec.alpha :as spec]
            [antoine.sql.parametros :refer [obtiene-nro-protocolo-ambulatorio obtiene-nro-protocolo-internado]]))


(defn generar-evaluacion-preanestesica
  []
  [0                                                               ;:anes_estado
   0                                                               ;:anes_numero
   (legajo-sin-digito-verificador legajo-anestesiologo)            ;:anes_codlegamed
   0                                                               ;:anes_tiplegamed
   (generar-intervencion)                                          ;:anes_interven
   0                                                               ;:anes_tipoint
   1                                                               ;:anes_secreali
   (:sistolica presion-min-max)                                    ;:anes_presionmax
   (:diastolica presion-min-max)                                   ;:anes_presionmin
   (generar-pulso)                                                 ;:anes_pulsofrec
   (gen/generate (spec/gen string?))                               ;:anes_pulsocarac
   4                                                               ;:anes_asa
   2                                                               ;:anes_tipocir
   1                                                               ;:anes_escalam
   (hora-actual)                                                   ;:anes_horini
   ""                                                              ;:anes_filler_1
   ""                                                              ;:anes_complipre_8
   ""                                                              ;:anes_complipre_7
   ""                                                              ;:anes_complipre_6
   ""                                                              ;:anes_complipre_5
   ""                                                              ;:anes_complipre_4
   ""                                                              ;:anes_complipre_3
   "X"                                                             ;:anes_complipre_2
   ""                                                              ;:anes_complipre_1
   ""                                                              ;:anes_clinpre_33
   ""                                                              ;:anes_clinpre_32
   ""                                                              ;:anes_clinpre_31
   ""                                                              ;:anes_clinpre_30
   ""                                                              ;:anes_clinpre_29
   ""                                                              ;:anes_clinpre_28
   ""                                                              ;:anes_clinpre_27
   ""                                                              ;:anes_clinpre_26
   ""                                                              ;:anes_clinpre_25
   ""                                                              ;:anes_clinpre_24
   ""                                                              ;:anes_clinpre_23
   ""                                                              ;:anes_clinpre_22
   ""                                                              ;:anes_clinpre_21
   ""                                                              ;:anes_clinpre_20
   ""                                                              ;:anes_clinpre_19
   ""                                                              ;:anes_clinpre_18
   ""                                                              ;:anes_clinpre_17
   ""                                                              ;:anes_clinpre_16
   ""                                                              ;:anes_clinpre_15
   ""                                                              ;:anes_clinpre_14
   ""                                                              ;:anes_clinpre_13
   "X"                                                             ;:anes_clinpre_12
   ""                                                              ;:anes_clinpre_11
   ""                                                              ;:anes_clinpre_10
   ""                                                              ;:anes_clinpre_9
   ""                                                              ;:anes_clinpre_8
   ""                                                              ;:anes_clipnre_7
   ""                                                              ;:anes_clinpre_6
   ""                                                              ;:anes_clinpre_5
   ""                                                              ;:anes_clinpre_4
   ""                                                              ;:anes_clinpre_3
   ""                                                              ;:anes_clinpre_2
   ""                                                              ;:anes_clinpre_1
   ""                                                              ;:anes_tratprev_19
   ""                                                              ;:anes_tratprev_18
   ""                                                              ;:anes_tratprev_17
   ""                                                              ;:anes_tratprev_16
   ""                                                              ;:anes_tratprev_15
   ""                                                              ;:anes_tratprev_14
   ""                                                              ;:anes_tratprev_13
   ""                                                              ;:anes_tratprev_12
   ""                                                              ;:anes_tratprev_11
   ""                                                              ;:anes_tratprev_10
   ""                                                              ;:anes_tratprev_9
   ""                                                              ;:anes_tratprev_8
   ""                                                              ;:anes_tratprev_7
   "X"                                                             ;:anes_tratprev_6
   ""                                                              ;:anes_tratprev_5
   ""                                                              ;:anes_tratprev_4
   ""                                                              ;:anes_tratprev_3
   ""                                                              ;:anes_tratprev_2
   ""                                                              ;:anes_tratprev_1
   ""                                                              ;:anes_tratpreobs
   ""                                                              ;:anes_cabcue_5
   ""                                                              ;:anes_cabcue_4
   "X"                                                             ;:anes_cabcue_3
   ""                                                              ;:anes_cabcue_2
   ""                                                              ;:anes_cabcue_1
   ""                                                              ;:anes_anesregio_5
   ""                                                              ;:anes_anesregio_4
   "X"                                                             ;:anes_anesregio_3
   ""                                                              ;:anes_anesregio_2
   ""                                                              ;:anes_anesregio_1
   ""                                                              ;:anes_anesregioobs
   ""                                                              ;:anes_exacompl_21
   ""                                                              ;:anes_exacompl_20
   ""                                                              ;:anes_exacompl_19
   "X"                                                             ;:anes_exacompl_18
   ""                                                              ;:anes_exacompl_17
   ""                                                              ;:anes_exacompl_16
   ""                                                              ;:anes_exacompl_15
   ""                                                              ;:anes_exacompl_14
   ""                                                              ;:anes_exacompl_13
   ""                                                              ;:anes_exacompl_12
   ""                                                              ;:anes_exacompl_11
   ""                                                              ;:anes_exacompl_10
   ""                                                              ;:anes_exacompl_9
   ""                                                              ;:anes_exacom_8
   ""                                                               ;:anes_exacompl_7
   ""                                                              ;:anes_exacompl_6
   ""                                                              ;:anes_exacompl_5
   ""                                                              ;:anes_exacompl_4
   ""                                                              ;:anes_exacompl_3
   ""                                                              ;:anes_exacompl_2
   ""                                                              ;:anes_exacompl_1
   ""                                                              ;:anes_exacomplobs
   ""                                                              ;:anes_psipre_6
   ""                                                              ;:anes_psipre_5
   ""                                                              ;:anes_psipre_4
   "X"                                                             ;:anes_psipre_3
   ""                                                              ;:anes_psipre_2
   ""                                                              ;:anes_psipre_1
   "X"                                                             ;:anes_exaesp_3
   ""                                                              ;:anes_exaesp_2
   ""                                                              ;:anes_exaesp_1
   ""                                                              ;:anes_exaespobs
   0                                                               ;:anes_nrotxt
   ""                                                              ;:anes_condicion
   0                                                               ;:anes_tipoblo
   0                                                               ;:anes_cateter
   0                                                               ;:anes_ihnend
   0                                                               ;:anes_intub
   0                                                               ;:anes_manguito
   0                                                               ;:anes_espasi
   0                                                               ;:anes_mecman
   0                                                               ;:anes_circuito
   0                                                               ;:anes_cerrado
   0                                                               ;:anes_apgar
   0                                                               ;:anes_horrecup
   0                                                               ;:anes_sector
   0                                                               ;:anes_anesoper_9
   0                                                               ;:anes_anesoper_8
   0                                                               ;:anes_anesoper_7
   0                                                               ;:anes_anesoper_6
   0                                                               ;:anes_anesoper_5
   0                                                               ;:anes_anesoper_4
   0                                                               ;:anes_anesoper_3
   0                                                               ;:anes_anesoper_2
   0                                                               ;:anes_anesoper_1
   0                                                               ;:anes_shock_3
   0                                                               ;:anes_shock_2
   0                                                               ;:anes_shock_1
   0                                                               ;:anes_esca_5
   0                                                               ;:anes_esca_4
   0                                                               ;:anes_esca_3
   0                                                               ;:anes_esca_2
   0                                                               ;:anes_esca_1
   ""                                                              ;:anes_filler_3
   ])

(defn generar-evaluacion-anestesica-completa
  []
  [1                                                       ;:anes_estado ¿Cerrar o no cerrar? 2 es cerrado
   0                                                       ;:anes_numero Si se cierra hay que agregar número acá 
   (legajo-sin-digito-verificador legajo-anestesiologo)    ;:anes_codlegamed
   0                                                       ;:anes_tiplegamed
   (generar-intervencion)                                  ;:anes_interven
   0                                                       ;:anes_tipoint
   2                                                       ;:anes_secreali
   (:sistolica presion-min-max)                            ;:anes_presionmax
   (:diastolica presion-min-max)                           ;:anes_presionmin
   (generar-pulso)                                         ;:anes_pulsofrec 
   "NORMAL"                                                ;:anes_pulsocarac
   1                                                       ;:anes_asa
   1                                                       ;:anes_tipocir
   3                                                       ;:anes_escalam
   (hora-actual)                                           ;:anes_horini
   ""                                                      ;:anes_filler_1
   ""                                                      ;:anes_complipre_8
   ""                                                      ;:anes_complipre_7
   ""                                                      ;:anes_complipre_6
   ""                                                      ;:anes_complipre_5
   ""                                                      ;:anes_complipre_4
   "X"                                                     ;:anes_complipre_3
   ""                                                      ;:anes_complipre_2
   ""                                                      ;:anes_complipre_1
   ""                                                      ;:anes_clinpre_33
   ""                                                      ;:anes_clinpre_32
   ""                                                      ;:anes_clinpre_31
   ""                                                      ;:anes_clinpre_30
   ""                                                      ;:anes_clinpre_29
   ""                                                      ;:anes_clinpre_28
   ""                                                      ;:anes_clinpre_27
   ""                                                      ;:anes_clinpre_26
   ""                                                      ;:anes_clinpre_25
   ""                                                      ;:anes_clinpre_24
   ""                                                      ;:anes_clinpre_23
   ""                                                      ;:anes_clinpre_22
   ""                                                      ;:anes_clinpre_21
   ""                                                      ;:anes_clinpre_20
   ""                                                      ;:anes_clinpre_19
   ""                                                      ;:anes_clinpre_18
   ""                                                      ;:anes_clinpre_17
   ""                                                      ;:anes_clinpre_16
   ""                                                      ;:anes_clinpre_15
   ""                                                      ;:anes_clinpre_14
   ""                                                      ;:anes_clinpre_13
   "X"                                                     ;:anes_clinpre_12
   ""                                                      ;:anes_clinpre_11
   ""                                                      ;:anes_clinpre_10
   ""                                                      ;:anes_clinpre_9
   ""                                                      ;:anes_clinpre_8
   ""                                                      ;:anes_clipnre_7
   ""                                                      ;:anes_clinpre_6
   ""                                                      ;:anes_clinpre_5
   ""                                                      ;:anes_clinpre_4
   ""                                                      ;:anes_clinpre_3
   ""                                                      ;:anes_clinpre_2
   ""                                                      ;:anes_clinpre_1
   ""                                                      ;:anes_tratprev_19
   ""                                                      ;:anes_tratprev_18
   "X"                                                     ;:anes_tratprev_17
   ""                                                      ;:anes_tratprev_16
   ""                                                      ;:anes_tratprev_15
   ""                                                      ;:anes_tratprev_14
   ""                                                      ;:anes_tratprev_13
   ""                                                      ;:anes_tratprev_12
   ""                                                      ;:anes_tratprev_11
   ""                                                      ;:anes_tratprev_10
   ""                                                      ;:anes_tratprev_9
   ""                                                      ;:anes_tratprev_8
   ""                                                      ;:anes_tratprev_7
   ""                                                      ;:anes_tratprev_6
   ""                                                      ;:anes_tratprev_5
   ""                                                      ;:anes_tratprev_4
   ""                                                      ;:anes_tratprev_3
   ""                                                      ;:anes_tratprev_2
   ""                                                      ;:anes_tratprev_1
   ""                                                      ;:anes_tratpreobs
   ""                                                      ;:anes_cabcue_5
   ""                                                      ;:anes_cabcue_4
   "X"                                                     ;:anes_cabcue_3
   ""                                                      ;:anes_cabcue_2
   ""                                                      ;:anes_cabcue_1
   ""                                                      ;:anes_anesregio_5
   ""                                                      ;:anes_anesregio_4
   ""                                                      ;:anes_anesregio_3
   "X"                                                     ;:anes_anesregio_2
   ""                                                      ;:anes_anesregio_1
   ""                                                      ;:anes_anesregioobs
   ""                                                      ;:anes_exacompl_21
   ""                                                      ;:anes_exacompl_20
   ""                                                      ;:anes_exacompl_19
   "X"                                                     ;:anes_exacompl_18
   ""                                                      ;:anes_exacompl_17
   ""                                                      ;:anes_exacompl_16
   ""                                                      ;:anes_exacompl_15
   ""                                                      ;:anes_exacompl_14
   ""                                                      ;:anes_exacompl_13
   ""                                                      ;:anes_exacompl_12
   ""                                                      ;:anes_exacompl_11
   ""                                                      ;:anes_exacompl_10
   ""                                                      ;:anes_exacompl_9
   ""                                                      ;:anes_exacom_8
   ""                                                      ;:anes_exacompl_7
   ""                                                      ;:anes_exacompl_6
   ""                                                      ;:anes_exacompl_5
   ""                                                      ;:anes_exacompl_4
   ""                                                      ;:anes_exacompl_3
   ""                                                      ;:anes_exacompl_2
   ""                                                      ;:anes_exacompl_1
   ""                                                      ;:anes_exacomplobs
   ""                                                      ;:anes_psipre_6
   ""                                                      ;:anes_psipre_5
   ""                                                      ;:anes_psipre_4
   "X"                                                     ;:anes_psipre_3
   ""                                                      ;:anes_psipre_2
   ""                                                      ;:anes_psipre_1
   "X"                                                     ;:anes_exaesp_3
   ""                                                      ;:anes_exaesp_2
   ""                                                      ;:anes_exaesp_1
   ""                                                      ;:anes_exaespobs
   0                                                       ;:anes_nrotxt
   "ESTABLE"                                               ;:anes_condicion
   1                                                       ;:anes_tipoblo
   1                                                       ;:anes_cateter
   1                                                       ;:anes_ihnend
   1                                                       ;:anes_intub
   1                                                       ;:anes_manguito
   1                                                       ;:anes_espasi
   0                                                       ;:anes_mecman
   1                                                       ;:anes_circuito
   0                                                       ;:anes_cerrado
   2                                                       ;:anes_apgar
   (plus-n-mins 30)                                        ;:anes_horrecup
   1                                                       ;:anes_sector
   0                                                       ;:anes_anesoper_9
   0                                                       ;:anes_anesoper_8
   1                                                       ;:anes_anesoper_7
   0                                                       ;:anes_anesoper_6
   1                                                       ;:anes_anesoper_5
   0                                                       ;:anes_anesoper_4
   0                                                       ;:anes_anesoper_3
   0                                                       ;:anes_anesoper_2
   1                                                       ;:anes_anesoper_1
   0                                                       ;:anes_shock_3
   0                                                       ;:anes_shock_2
   1                                                       ;:anes_shock_1
   3                                                       ;:anes_esca_5
   3                                                       ;:anes_esca_4
   3                                                       ;:anes_esca_3
   0                                                       ;:anes_esca_2
   0                                                       ;:anes_esca_1
   ""                                                      ;:anes_filler_3
   ])

(defn generar-ficha-anestesica
  [cerrada?])




 
(comment
  (gen/generate (spec/gen :seguridad/material-entregable))
  (gen/generate (spec/gen legajos-personal))

  (repeatedly 100 generar-intervencion)
     (generar-intervencion)
  
  (ns-unmap *ns* 'generar-seguridad-quirurgica)

  (count (generar-seguridad-quirurgica :completa-con-anestesia))
  (count (generar-seguridad-quirurgica :parcial-con-anestesia))
  
  (count (generar-protocolo-internado-iniciado))

(generar-presiones-arteriales)
  
  (sort (list :ciriprotocolo 
                   :ciriiepartero 
                   :ciriieneonato 
                   :ciripatologia 
                   :cirileghemote 
                   :cirilegmonito 
                   :cirilegperfus 
                   :cirilegconsenti 
                   :ciriieconsenti 
                   :ciriresponde 
                   :ciricodnome 
                   :ciritiponome 
                   :ciriieciru 
                   :cirilegciru 
                   :cirinroquirofa 
                   :cirifechafinal 
                   :cirihorafinal 
                   :ciripinzasinicio
                   :cirifechainicio 
                   :cirihorainicio 
                   :ciriestado 
                   :ciriconsenti 
                   :ciriserieprot 
                   :cirimarcaprot 
                   :cirifechabanio 
                   :cirihorabanio 
                   :cirifecharasura 
                   :cirihorarasura 
                   :ciriconquerasura
                   :cirinrosolpatol 
                   :ciriintensi 
                   :cirihorasint 
                   :ciricantint 
                   :ciriusoo2 
                   :ciriobsttipo 
                   :ciriobstgesta 
                   :cirilegatecnico 
                   :ciritipotecnico 
                   :ciripinzasinicio
                   :ciripinzasfinal 
                   :cirigasasfinal 
                   :ciripasorend 
                   :ciriusolaserargo
                   :ciriusomicroscop
                   :ciriusolaparasco))
  )