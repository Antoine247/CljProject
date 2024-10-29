(ns antoine.especificaciones.generador-protocolo-internado
  (:require [antoine.sql.parametros :refer [obtiene-nro-protocolo-internado]]
            [antoine.utils.utils :refer [fecha-actual hora-actual plus-n-mins]]
            [antoine.especificaciones.generadores-utils :refer [generar-nombre
                                                                legajos-personal
                                                                legajo-anestesiologo
                                                                legajo-medico
                                                                legajo-sin-digito-verificador
                                                                generar-intervencion
                                                                generar-presiones-arteriales
                                                                generar-pulso
                                                                presion-min-max]]))

(defn generar-protocolo-internado-iniciado
  []
  (let [protocolo (-> (obtiene-nro-protocolo-internado) first :param/prm_54)]
    [protocolo                                               ;:ciriprotocolo
     0                                                       ;:ciriiepartero
     0                                                       ;:ciriieneonato
     0                                                       ;:ciripatologia
     ""                                                      ;:cirileghemote
     ""                                                      ;:cirilegmonito
     ""                                                      ;:cirilegperfus
     0                                                       ;:cirilegconsenti
     0                                                       ;:ciriieconsenti
     0                                                       ;:ciriresponde
     0                                                       ;:ciricodnome
     0                                                       ;:ciritiponome
     0                                                       ;:ciriieciru
     0                                                       ;:cirilegciru
     0                                                       ;:cirinroquirofa
     0                                                       ;:cirifechafinal
     0                                                       ;:cirihorafinal 
     0                                                       ;:cirifechainicio
     0                                                       ;:cirihorainicio
     0                                                       ;:ciriestado
     ""                                                      ;:ciriconsenti
     ""                                                      ;:ciriserieprot
     ""                                                      ;:cirimarcaprot
     0                                                       ;:cirifechabanio
     0                                                       ;:cirihorabanio
     0                                                       ;:cirifecharasura
     0                                                       ;:cirihorarasura
     0                                                       ;:ciriconquerasura
     0                                                       ;:cirinrosolpatol
     0                                                       ;:ciriintensi
     0                                                       ;:cirihorasint
     0                                                       ;:ciricantint
     0                                                       ;:ciriusoo2 
     0                                                       ;:ciriobsttipo 
     0                                                       ;:ciriobstgesta
     0                                                       ;:cirilegatecnico
     0                                                       ;:ciritipotecnico
     0                                                       ;:ciripinzasinicio
     0                                                       ;:ciripinzasfinal
     0                                                       ;:cirigasasfinal
     0                                                       ;:ciripasorend
     0                                                       ;:ciriusolaserargon
     0                                                       ;:ciriusomicroscopio
     0                                                       ;:ciriusolaparascopio
     ]))

(defn generar-protocolo-internado
  []
  (let [hora-inicio (hora-actual)
        hora-fin (plus-n-mins 60)
        fecha-inicio (fecha-actual)
        fecha-fin fecha-inicio
        pinzas-ini (rand-nth (range 1 11))
        pinzas-fin pinzas-ini
        protocolo (-> (obtiene-nro-protocolo-internado) first :param/prm_54)]
    [protocolo                                                             ;:ciriprotocolo
     0                                                                     ;:ciriiepartero
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
  (let [protocolo (-> (obtiene-nro-protocolo-internado) first :param/prm_54)]
    [protocolo ;:ciriprotocolo
      ;:ciriiepartero
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
     ]))

(defn generar-protocolo-internado-extraccion-implantes
  []
  (let [protocolo (-> (obtiene-nro-protocolo-internado) first :param/prm_54)]
    [protocolo                                                 ;: ciriprotocolo
      ;:ciriiepartero
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
     ]))

(defn generar-protocolo-internado-anatomia-patologica
  []
  (let [protocolo (-> (obtiene-nro-protocolo-internado) first :param/prm_54)]
    [protocolo ;:ciriprotocolo
      ;:ciriiepartero
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
     ]))