(ns antoine.sql.cirugia-internado
  (:require [antoine.sql.anatomia-patologica :as anatpa]
            [antoine.servicios.conexiones :refer [consulta-asistencial]]
            [honey.sql :as sql]
            [antoine.especificaciones.generadores :refer [generar-protocolo-internado-iniciado
                                                          generar-protocolo-internado
                                                          generar-protocolo-internado-con-implantes
                                                          generar-protocolo-internado-extraccion-implantes
                                                          generar-protocolo-internado-anatomia-patologica]]
            [antoine.especificaciones.generadores-utils :refer [legajo-sin-digito-verificador
                                                                legajo-medico
                                                                generar-intervencion]]
            [antoine.sql.numeradores :as numeradores]))

(defn insertar
  "Las opciones serían :hemodinamia, :parto o nil, el status :inicializada :completa :completa-con-implantes :completa-con-extraccion-implante 
   :completa-con-anatomia-patologica y anestesia? un booleano"
  [{:keys [tbc_admision_scroll/Adm_HistClin tbc_admision_scroll/Adm_FecIng tbc_admision_scroll/Adm_HorIng opciones status anestesia?] :as pac}]
  (let [{:keys [tipointerven parto_ intervencion neonatologo partero]} (cond
                                                                 (= opciones :hemodinamia) {:tipointerven 2
                                                                                            :intervencion (generar-intervencion)
                                                                                            :neonatologo 0
                                                                                            :parto_ 0
                                                                                            :partero 0}
                                                                 (= opciones :parto) {:tipointerven 0
                                                                                      :intervencion (rand-nth [161 162 878])
                                                                                      :neonatologo (legajo-sin-digito-verificador legajo-medico)
                                                                                      :parto_ 1
                                                                                      :partero (legajo-sin-digito-verificador legajo-medico)}
                                                                 :else {:tipointerven 1
                                                                        :intervencion (generar-intervencion)
                                                                        :neonatologo 0
                                                                        :parto_ 0
                                                                        :partero 0})
        anestesia (if anestesia? 1 0)
        fn-generacion (cond
                        (= status :inicializada) generar-protocolo-internado-iniciado
                        (= status :completa) generar-protocolo-internado
                        (= status :completa-con-implantes) generar-protocolo-internado-con-implantes
                        (= status :completa-con-extraccion-implante) generar-protocolo-internado-extraccion-implantes
                        (= status :completa-con-anatomia-patologica) generar-protocolo-internado-anatomia-patologica
                        :else (throw (IllegalArgumentException. "Opción inválida")))
        protocolo (numeradores/obtener-nro-protocolo)
        valores (concat
                 [Adm_HistClin
                  Adm_FecIng
                  Adm_HorIng
                  Adm_FecIng
                  Adm_HorIng
                  tipointerven
                  anestesia
                  intervencion 
                  partero
                  neonatologo 
                  parto_
                  protocolo]
                 (fn-generacion)) 
        consulta (sql/format {:insert-into :tbc_cirugint
                              :columns [:cirihistcl
                                        :cirifechacarga
                                        :cirihoracarga
                                        :cirifechacarga1
                                        :cirihoracarga1
                                        :ciritipointerven
                                        :cirianestesia
                                        :ciriinterven
                                        :cirilegpartero
                                        :cirilegneonato
                                        :ciriparto
                                        :ciriprotocolo
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
                                        :ciriobsttipo ;; siempre 0
                                        :ciriobstgesta
                                        :cirilegatecnico
                                        :ciritipotecnico
                                        :ciripinzasinicio
                                        :ciripinzasfinal
                                        :cirigasasfinal
                                        :ciripasorend
                                        :ciriusolaserargon
                                        :ciriusomicroscopio
                                        :ciriusolaparascopio]
                              :values [valores]})]
   (when (and (consulta-asistencial consulta) (anatpa/insertar pac))
     pac)))
 
(comment
  
  (insertar {:tbc_admision_scroll/Adm_HistClin 3223490
             :tbc_admision_scroll/Adm_FecIng 20240618
             :tbc_admision_scroll/Adm_HorIng 1654
             :opciones nil 
             :status :completa 
             :anestesia? false})
  
  (def campos '(CiriHistCl
                CiriFechaCarga
                CiriHoraCarga
                CiriTipoNome
                CiriCodNome
                CiriProtocolo
                CiriFechaCarga1
                CiriHoraCarga1
                CiriFechaInicio
                CiriHoraInicio
                CiriFechaFinal
                CiriHoraFinal
                CiriLegCiru
                CiriIeCiru
                CiriInterven
                CiriAnestesia
                CiriPatologia
                CiriEstado
                CiriConsenti
                CiriSerieProt
                CiriLegPartero
                CiriIePartero
                CiriLegNeonato
                CiriIeNeonato
                CiriLegHemote
                CiriLegMonito
                CiriMarcaProt
                CiriLegPerfus
                CiriLegConsenti
                CiriIeConsenti
                CiriResponde
                CiriFechaBanio
                CiriHoraBanio
                CiriFechaRasura
                CiriHoraRasura
                CiriConQueRasura
                CiriNroQuirofa
                CiriNrosolPatol
                CiriIntensi
                CiriHorasInt
                CiriCantInt
                CiriUsoO2
                CiriParto
                CiriObstTipo
                CiriObstGesta
                CiriLegaTecnico
                CiriTipoTecnico
                CiriPinzasInicio
                CiriPinzasFinal
                CiriGasasFinal
                CiriTipoInterven
                CiriPasoRend
                CiriUsoLaserArgon
                CiriUsoMicroscopio
                CiriUsoLaparascopio))
  (count campos)

  (def columnas [:cirihistcl
                 :cirifechacarga
                 :cirihoracarga
                 :cirifechacarga1
                 :cirihoracarga1
                 :ciritipointerven
                 :cirianestesia
                 :ciriinterven
                 :cirilegpartero
                 :cirilegneonato
                 :ciriparto
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
                 :ciriobsttipo ;; siempre 0
                 :ciriobstgesta
                 :cirilegatecnico
                 :ciritipotecnico
                 :ciripinzasinicio
                 :ciripinzasfinal
                 :cirigasasfinal
                 :ciripasorend
                 :ciriusolaserargon
                 :ciriusomicroscopio
                 :ciriusolaparascopio])
  (count columnas)

 (tap> 
  (clojure.pprint/print-table (mapv merge 
                                    (mapv #(assoc {} :campos %) (sort campos))  
                                    (mapv #(assoc {} :columnas %) (sort columnas))))) 
  
  :rfc)