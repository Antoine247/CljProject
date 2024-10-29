(ns antoine.especificaciones.generador-seguridadquirurgica
  (:require [antoine.especificaciones.generadores-utils :refer [generar-nombre
                                                                legajos-personal
                                                                legajo-anestesiologo
                                                                legajo-medico
                                                                legajo-sin-digito-verificador
                                                                generar-intervencion
                                                                generar-presiones-arteriales
                                                                generar-pulso
                                                                presion-min-max]]
            [antoine.utils.utils :refer [fecha-actual hora-actual plus-n-mins]]))

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
