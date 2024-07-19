(ns antoine.especificaciones.generadores
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
            [clojure.spec.alpha :as spec]))

(defmulti generar-seguridad-quirurgica (fn [_] (identity _)))

(defmethod generar-seguridad-quirurgica :default [_] (throw (IllegalArgumentException. "Opción no implementada")))

(defmethod generar-seguridad-quirurgica :parcial-con-anestesia
  [_]
  (let [admin (legajo-sin-digito-verificador legajos-personal)
        circulante (legajo-sin-digito-verificador legajos-personal)
        anestesiologo (legajo-sin-digito-verificador legajo-anestesiologo)
        cirujano (legajo-sin-digito-verificador legajo-medico)]
    [(fecha-actual)                                                     ; :segfechacarga   
     1                                                                  ; :segcirculmate
     0                                                                  ; :segtipoadmin 
     admin                                                              ; :seglegaadmin
     1                                                                  ; :segtipocirculini
     circulante                                                         ; :seglegacirculini 
     2                                                                  ; :seganestalergia
     0                                                                  ; :segestado
     0                                                                  ; :segadminident
     1                                                                  ; :segadmindiag
     0                                                                  ; :segadminconsen
     1                                                                  ; :segcirculident
     1                                                                  ; :segcirculbanio
     1                                                                  ; :segcirculhiscli
     1                                                                  ; :segcirculestu
     1                                                                  ; :segcirculprote
     1                                                                  ; :segcirculconsen
     2                                                                  ; :seganestident
     2                                                                  ; :seganestpulso
     2                                                                  ; :seganestseguri
     "alergia X"                                                        ; :seganestcuales
     2                                                                  ; :seganestviaaerea
     2                                                                  ; :seganestacceso
     2                                                                  ; :seganestsangre
     anestesiologo                                                      ; :seglegaanestini
     0                                                                  ; :segtipoanestini
     1                                                                  ; :segciruident
     1                                                                  ; :segciruantibiot
     1                                                                  ; :segcirubisturi
     1                                                                  ; :segciruincidentes
     cirujano                                                           ; :seglegaciruini
     0                                                                  ; :segtipociruini
     2                                                                  ; :seganestrepasaini
     anestesiologo                                                      ; :seglegaanestrepi
     0                                                                  ; :segtipoanestrepi
     2                                                                  ; :seginstrurepasaini
     2                                                                  ; :seginstrugasasini
     1                                                                  ; :seginstrupinzasini
     circulante                                                         ; :seglegainstruini
     0                                                                  ; :segtipoinstruini
     0                                                                  ; :segciruproced
     0                                                                  ; :segciruindica
     0                                                                  ; :seglegacirufin
     0                                                                  ; :segtipocirufin
     0                                                                  ; :seganestplan  
     0                                                                  ; :seglegaanestfin
     0                                                                  ; :segtipoanestfin
     0                                                                  ; :seginstrugasasfin
     0                                                                  ; :seginstrupinzasfin
     0                                                                  ; :seglegainstrufin
     0                                                                  ; :segtipoinstrufin
     0                                                                  ; :segcirculmuestras
     0                                                                  ; :segcirculregistro
     0                                                                  ; :seglegacirculfin
     0                                                                  ; :segtipocirculfin
     2                                                                  ; :seganestocular
     2                                                                  ; :seganestdecubi
     2                                                                  ; :seganestcomorb
     0                                                                  ; :segcirculproinstru
     0                                                                  ; :segcirculnormot
     0                                                                  ; :segcirculparteciru
     0                                                                  ; :segcirculparteanes
     2                                                                  ; :segcirculequipres
     2                                                                  ; :segcirculequifunc
     2                                                                  ; :segcirculidsipr
     2                                                                  ; :segcirculdecubi
     2                                                                  ; :segcirculimprev
     2                                                                  ; :segcirculanesproblema
     circulante                                                         ; :seglegacirculcut
     0                                                                  ; :segtipocirculcut
     ]))

(defmethod generar-seguridad-quirurgica :parcial-sin-anestesia
  [_]
  (let [admin (legajo-sin-digito-verificador legajos-personal)
        circulante (legajo-sin-digito-verificador legajos-personal) 
        cirujano (legajo-sin-digito-verificador legajo-medico)]
    [(fecha-actual)                                                     ; :segfechacarga   
     2                                                                  ; :segcirculmate
     0                                                                  ; :segtipoadmin 
     admin                                                              ; :seglegaadmin
     1                                                                  ; :segtipocirculini
     circulante                                                         ; :seglegacirculini 
     1                                                                  ; :seganestalergia
     0                                                                  ; :segestado
     0                                                                  ; :segadminident
     1                                                                  ; :segadmindiag
     0                                                                  ; :segadminconsen
     1                                                                  ; :segcirculident
     1                                                                  ; :segcirculbanio
     1                                                                  ; :segcirculhiscli
     1                                                                  ; :segcirculestu
     1                                                                  ; :segcirculprote
     1                                                                  ; :segcirculconsen
     1                                                                  ; :seganestident
     1                                                                  ; :seganestpulso
     1                                                                  ; :seganestseguri
     ""                                                                 ; :seganestcuales
     1                                                                  ; :seganestviaaerea
     1                                                                  ; :seganestacceso
     1                                                                  ; :seganestsangre
     999999                                                             ; :seglegaanestini
     9                                                                  ; :segtipoanestini
     1                                                                  ; :segciruident
     1                                                                  ; :segciruantibiot
     1                                                                  ; :segcirubisturi
     1                                                                  ; :segciruincidentes
     cirujano                                                           ; :seglegaciruini
     0                                                                  ; :segtipociruini
     1                                                                  ; :seganestrepasaini
     999999                                                             ; :seglegaanestrepi
     9                                                                  ; :segtipoanestrepi
     2                                                                  ; :seginstrurepasaini
     2                                                                  ; :seginstrugasasini
     1                                                                  ; :seginstrupinzasini
     circulante                                                         ; :seglegainstruini
     0                                                                  ; :segtipoinstruini
     0                                                                  ; :segciruproced
     0                                                                  ; :segciruindica
     0                                                                  ; :seglegacirufin
     0                                                                  ; :segtipocirufin
     0                                                                  ; :seganestplan  
     0                                                                  ; :seglegaanestfin
     0                                                                  ; :segtipoanestfin
     0                                                                  ; :seginstrugasasfin
     0                                                                  ; :seginstrupinzasfin
     0                                                                  ; :seglegainstrufin
     0                                                                  ; :segtipoinstrufin
     0                                                                  ; :segcirculmuestras
     0                                                                  ; :segcirculregistro
     0                                                                  ; :seglegacirculfin
     0                                                                  ; :segtipocirculfin
     2                                                                  ; :seganestocular
     2                                                                  ; :seganestdecubi
     2                                                                  ; :seganestcomorb
     0                                                                  ; :segcirculproinstru
     0                                                                  ; :segcirculnormot
     0                                                                  ; :segcirculparteciru
     0                                                                  ; :segcirculparteanes
     2                                                                  ; :segcirculequipres
     2                                                                  ; :segcirculequifunc
     2                                                                  ; :segcirculidsipr
     2                                                                  ; :segcirculdecubi
     2                                                                  ; :segcirculimprev
     2                                                                  ; :segcirculanesproblema
     circulante                                                         ; :seglegacirculcut
     0                                                                  ; :segtipocirculcut
     ]))

(defmethod generar-seguridad-quirurgica :completa-con-anestesia
  [_]
  (let [admin (legajo-sin-digito-verificador legajos-personal)
        circulante (legajo-sin-digito-verificador legajos-personal)
        anestesiologo (legajo-sin-digito-verificador legajo-anestesiologo)
        cirujano (legajo-sin-digito-verificador legajo-medico)]
    [(fecha-actual)                                                     ; :segfechacarga   
     1                                                                  ; :segcirculmate
     0                                                                  ; :segtipoadmin 
     admin                                                              ; :seglegaadmin
     1                                                                  ; :segtipocirculini
     circulante                                                         ; :seglegacirculini 
     1                                                                  ; :seganestalergia
     0                                                                  ; :segestado
     0                                                                  ; :segadminident
     1                                                                  ; :segadmindiag
     0                                                                  ; :segadminconsen
     1                                                                  ; :segcirculident
     1                                                                  ; :segcirculbanio
     1                                                                  ; :segcirculhiscli
     1                                                                  ; :segcirculestu
     1                                                                  ; :segcirculprote
     1                                                                  ; :segcirculconsen
     2                                                                  ; :seganestident
     2                                                                  ; :seganestpulso
     2                                                                  ; :seganestseguri
     ""                                                                 ; :seganestcuales
     2                                                                  ; :seganestviaerea
     2                                                                  ; :seganestacceso
     2                                                                  ; :seganestsangre
     anestesiologo                                                      ; :seglegaanestini
     0                                                                  ; :segtipoanestini
     1                                                                  ; :segciruident
     1                                                                  ; :segciruantibiot
     1                                                                  ; :segcirubisturi
     1                                                                  ; :segciruincidentes
     cirujano                                                           ; :seglegaciruini
     0                                                                  ; :segtipociruini
     2                                                                  ; :seganestrepasaini
     anestesiologo                                                      ; :seglegaanestrepl
     0                                                                  ; :segtipoanestrepl
     2                                                                  ; :seginstrurepasaini
     2                                                                  ; :seginstrugasasini
     1                                                                  ; :seginstrupinzasini
     circulante                                                         ; :seglegainstruini
     0                                                                  ; :segtipoinstruini
     1                                                                  ; :segciruproced
     1                                                                  ; :segciruindica
     cirujano                                                           ; :seglegacirufin
     0                                                                  ; :segtipocirufin
     2                                                                  ; :seganestplan 
     anestesiologo                                                      ; :seglegaanestfin
     0                                                                  ; :segtipoanestfin
     2                                                                  ; :seginstrugasasfin
     1                                                                  ; :seginstrupinzasfin
     circulante                                                         ; :seglegainstrufin
     0                                                                  ; :segtipoinstrufin
     2                                                                  ; :segcirculmuestras
     1                                                                  ; :segcirculregistro
     circulante                                                         ; :segcirculfin
     0                                                                  ; :segtipocirculfin
     2                                                                  ; :seganestocular
     2                                                                  ; :seganestdecubi
     2                                                                  ; :seganestcomorb
     2                                                                  ; :segcirculproinstru
     2                                                                  ; :segcirculnormot
     2                                                                  ; :segcirculparteciru
     2                                                                  ; :segcirculparteanes
     2                                                                  ; :segcirculequipres
     2                                                                  ; :segcirculequifunc
     2                                                                  ; :segcirculldsipr
     2                                                                  ; :segcirculdecubi
     2                                                                  ; :segcirculimprev
     2                                                                  ; :segcirculanesproblema
     circulante                                                         ; :seglegacirculcut
     0                                                                  ; :segtipocirculcut
     ]))

(defmethod generar-seguridad-quirurgica :completa-sin-anestesia
  [_]
  (let [admin (legajo-sin-digito-verificador legajos-personal)
        circulante (legajo-sin-digito-verificador legajos-personal) 
        cirujano (legajo-sin-digito-verificador legajo-medico)]
    [(fecha-actual)                                                     ; :segfechacarga   
     1                                                                  ; :segcirculmate
     0                                                                  ; :segtipoadmin 
     admin                                                              ; :seglegaadmin
     1                                                                  ; :segtipocirculini
     circulante                                                         ; :seglegacirculini 
     1                                                                  ; :seganestalergia
     0                                                                  ; :segestado
     0                                                                  ; :segadminident
     1                                                                  ; :segadmindiag
     0                                                                  ; :segadminconsen
     1                                                                  ; :segcirculident
     1                                                                  ; :segcirculbanio
     1                                                                  ; :segcirculhiscli
     1                                                                  ; :segcirculestu
     1                                                                  ; :segcirculprote
     1                                                                  ; :segcirculconsen
     1                                                                  ; :seganestident
     1                                                                  ; :seganestpulso
     1                                                                  ; :seganestseguri
     ""                                                                 ; :seganestcuales
     1                                                                  ; :seganestviaerea
     1                                                                  ; :seganestacceso
     1                                                                  ; :seganestsangre
     999999                                                             ; :seglegaanestini
     9                                                                  ; :segtipoanestini
     1                                                                  ; :segciruident
     1                                                                  ; :segciruantibiot
     1                                                                  ; :segcirubisturi
     1                                                                  ; :segciruincidentes
     cirujano                                                           ; :seglegaciruini
     0                                                                  ; :segtipociruini
     1                                                                  ; :seganestrepasaini
     999999                                                             ; :seglegaanestrepl
     9                                                                  ; :segtipoanestrepl
     2                                                                  ; :seginstrurepasaini
     2                                                                  ; :seginstrugasasini
     1                                                                  ; :seginstrupinzasini
     circulante                                                         ; :seglegainstruini
     0                                                                  ; :segtipoinstruini
     1                                                                  ; :segciruproced
     1                                                                  ; :segciruindica
     cirujano                                                           ; :seglegacirufin
     0                                                                  ; :segtipocirufin
     1                                                                  ; :seganestplan 
     999999                                                             ; :seglegaanestfin
     0                                                                  ; :segtipoanestfin
     2                                                                  ; :seginstrugasasfin
     1                                                                  ; :seginstrupinzasfin
     circulante                                                         ; :seglegainstrufin
     0                                                                  ; :segtipoinstrufin
     2                                                                  ; :segcirculmuestras
     1                                                                  ; :segcirculregistro
     circulante                                                         ; :segcirculfin
     0                                                                  ; :segtipocirculfin
     1                                                                  ; :seganestocular
     1                                                                  ; :seganestdecubi
     1                                                                  ; :seganestcomorb
     1                                                                  ; :segcirculproinstru
     2                                                                  ; :segcirculnormot
     2                                                                  ; :segcirculparteciru
     2                                                                  ; :segcirculparteanes
     2                                                                  ; :segcirculequipres
     2                                                                  ; :segcirculequifunc
     2                                                                  ; :segcirculldsipr
     2                                                                  ; :segcirculdecubi
     2                                                                  ; :segcirculimprev
     2                                                                  ; :segcirculanesproblema
     circulante                                                         ; :seglegacirculcut
     0                                                                  ; :segtipocirculcut
     ]))

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

(defn generar-protocolo-internado-iniciado
  []
  [;:ciriiepartero
   ;:ciriieneonato
   ;:ciripatologia
   ;:cirileghemote
   ;:cirilegmonito
   ;:cirilegperfus
   ;:cirilegconsenti
   ;:ciriieconsenti
   ;:ciriresponde
   ;:ciricodnome
   ;:ciritiponome
   ;:ciriieciru
   ;:cirilegciru
   ;:cirinroquirofa
   ;:cirifechafinal
   ;:cirihorafinal
   ;:ciripinzasinicio
   ;:cirifechainicio
   ;:cirihorainicio
   ;:ciriestado
   ;:ciriconsenti
   ;:ciriserieprot
   ;:cirimarcaprot
   ;:cirifechabanio
   ;:cirihorabanio
   ;:cirifecharasura
   ;:cirihorarasura
   ;:ciriconquerasura
   ;:cirinroquirofa
   ;:cirinrosolpatol
   ;:ciriintensi
   ;:cirihorasint
   ;:ciricantint
   ;:ciriusoo2
   0 ;:ciriobsttipo 
   ;:ciriobstgesta
   ;:cirilegatecnico
   ;:ciritipotecnico
   ;:ciripinzasinicio
   ;:ciripinzasfinal
   ;:cirigasasfinal
   ;:ciripasorend
   ;:ciriusolaserargon
   ;:ciriusomicroscopio
   ;:ciriusolaparascopio
   ])

(defn generar-protocolo-internado
  [] 
  (let [hora-inicio (hora-actual)
        hora-fin (plus-n-mins 60)
        fecha-inicio (fecha-actual)
        fecha-fin fecha-inicio
        pinzas-ini (rand-nth (range 1 11))
        pinzas-fin pinzas-ini] 
    [0                                                                     ;:ciriiepartero
     0                                                                     ;:ciriieneonato
     0                                                                     ;:ciripatologia
     (generar-nombre)                                                      ;:cirileghemote
     (generar-nombre)                                                      ;:cirilegmonito
     (generar-nombre)                                                      ;:cirilegperfus
     (legajo-sin-digito-verificador legajos-personal)                      ;:cirilegconsenti
     2                                                                     ;:ciriieconsenti
     2                                                                     ;:ciriresponde 
     0                                                                     ;:ciricodnome 
     0                                                                     ;:ciritiponome
     1                                                                     ;:ciriieciru
     (legajo-sin-digito-verificador legajo-medico)                         ;:cirilegciru
     (rand-nth (range 1 12))                                               ;:cirinroquirofa
     fecha-fin                                                             ;:cirifechafinal
     hora-fin                                                              ;:cirihorafinal 
     fecha-inicio                                                          ;:cirifechainicio
     hora-inicio                                                           ;:cirihorainicio
     1                                                                     ;:ciriestado
     "S"                                                                   ;:ciriconsenti
     ""                                                                    ;:ciriserieprot
     ""                                                                    ;:cirimarcaprot
     fecha-inicio                                                          ;:cirifechabanio
     hora-inicio                                                           ;:cirihorabanio
     fecha-inicio                                                          ;:cirifecharasura
     hora-inicio                                                           ;:cirihorarasura
     (rand-nth (range 0 3))                                                ;:ciriconquerasura
     0                                                                     ;:cirinrosolpatol 
     (rand-nth (range 0 3))                                                ;:ciriintensi 
     0                                                                     ;:cirihorasint
     2                                                                     ;:ciricantint
     0                                                                     ;:ciriusoo2 
     0                                                                     ;:ciriobsttipo 
     0                                                                     ;:ciriobstgesta
     (legajo-sin-digito-verificador legajos-personal)                      ;:cirilegatecnico
     0                                                                     ;:ciritipotecnico
     pinzas-ini                                                            ;:ciripinzasinicio
     pinzas-fin                                                            ;:ciripinzasfinal
     2                                                                     ;:cirigasasfinal
     0                                                                     ;:ciripasorend
     (rand-nth '(0 1))                                                     ;:ciriusolaserargon
     (rand-nth '(0 1))                                                     ;:ciriusomicroscopio
     (rand-nth '(0 1))                                                     ;:ciriusolaparascopio
                       ]))

(defn generar-protocolo-internado-con-implantes
  []
  [;:ciriiepartero
   ;:ciriieneonato
   ;:ciripatologia
   ;:cirileghemote
   ;:cirilegmonito
   ;:cirilegperfus
   ;:cirilegconsenti
   ;:ciriieconsenti
   ;:ciriresponde
   ;:ciricodnome
   ;:ciritiponome
   ;:ciriieciru
   ;:cirilegciru
   ;:cirinroquirofa
   ;:cirifechafinal
   ;:cirihorafinal
   ;:ciripinzasinicio
   ;:cirifechainicio
   ;:cirihorainicio
   1;:ciriestado
   ;:ciriconsenti
   ;:ciriserieprot
   ;:cirimarcaprot
   ;:cirifechabanio
   ;:cirihorabanio
   ;:cirifecharasura
   ;:cirihorarasura
   ;:ciriconquerasura
   ;:cirinroquirofa
   ;:cirinrosolpatol
   ;:ciriintensi
   ;:cirihorasint
   ;:ciricantint
   ;:ciriusoo2
   0 ;:ciriobsttipo 
   ;:ciriobstgesta
   ;:cirilegatecnico
   ;:ciritipotecnico
   ;:ciripinzasinicio
   ;:ciripinzasfinal
   ;:cirigasasfinal
   ;:ciripasorend
   ;:ciriusolaserargon
   ;:ciriusomicroscopio
   ;:ciriusolaparascopio
   ])

(defn generar-protocolo-internado-extraccion-implantes
  []
  [;:ciriiepartero
   ;:ciriieneonato
   ;:ciripatologia
   ;:cirileghemote
   ;:cirilegmonito
   ;:cirilegperfus
   ;:cirilegconsenti
   ;:ciriieconsenti
   ;:ciriresponde
   ;:ciricodnome
   ;:ciritiponome
   ;:ciriieciru
   ;:cirilegciru
   ;:cirinroquirofa
   ;:cirifechafinal
   ;:cirihorafinal
   ;:ciripinzasinicio
   ;:cirifechainicio
   ;:cirihorainicio
   1;:ciriestado
   ;:ciriconsenti
   ;:ciriserieprot
   ;:cirimarcaprot
   ;:cirifechabanio
   ;:cirihorabanio
   ;:cirifecharasura
   ;:cirihorarasura
   ;:ciriconquerasura
   ;:cirinroquirofa
   ;:cirinrosolpatol
   ;:ciriintensi
   ;:cirihorasint
   ;:ciricantint
   ;:ciriusoo2
   0 ;:ciriobsttipo 
   ;:ciriobstgesta
   ;:cirilegatecnico
   ;:ciritipotecnico
   ;:ciripinzasinicio
   ;:ciripinzasfinal
   ;:cirigasasfinal
   ;:ciripasorend
   ;:ciriusolaserargon
   ;:ciriusomicroscopio
   ;:ciriusolaparascopio
   ])

(defn generar-protocolo-internado-anatomia-patologica
  []
  [;:ciriiepartero
   ;:ciriieneonato
   ;:ciripatologia
   ;:cirileghemote
   ;:cirilegmonito
   ;:cirilegperfus
   ;:cirilegconsenti
   ;:ciriieconsenti
   ;:ciriresponde
   ;:ciricodnome
   ;:ciritiponome
   ;:ciriieciru
   ;:cirilegciru
   ;:cirinroquirofa
   (fecha-actual);:cirifechafinal
   (plus-n-mins 120);:cirihorafinal
   ;:ciripinzasinicio
   (fecha-actual);:cirifechainicio
   (hora-actual);:cirihorainicio
   1;:ciriestado
   ;:ciriconsenti
   ;:ciriserieprot
   ;:cirimarcaprot
   ;:cirifechabanio
   ;:cirihorabanio
   ;:cirifecharasura
   ;:cirihorarasura
   ;:ciriconquerasura
   ;:cirinroquirofa
   ;:cirinrosolpatol
   ;:ciriintensi
   ;:cirihorasint
   ;:ciricantint
   ;:ciriusoo2
   0 ;:ciriobsttipo 
   ;:ciriobstgesta
   ;:cirilegatecnico
   ;:ciritipotecnico
   ;:ciripinzasinicio
   ;:ciripinzasfinal
   ;:cirigasasfinal
   ;:ciripasorend
   ;:ciriusolaserargon
   ;:ciriusomicroscopio
   ;:ciriusolaparascopio
   ])
 
(comment
  (gen/generate (spec/gen :seguridad/material-entregable))
  (gen/generate (spec/gen legajos-personal))

  (repeatedly 100 generar-intervencion)
     (generar-intervencion)
  
  (ns-unmap *ns* 'generar-seguridad-quirurgica)

  (count (generar-seguridad-quirurgica :completa-con-anestesia))
  (count (generar-seguridad-quirurgica :parcial-con-anestesia))
  
(generar-presiones-arteriales)
  )