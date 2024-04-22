(ns antoine.sql.cirugia-internado
  (:require [antoine.sql.anatomia-patologica :as anatpa]
            [antoine.servicios.conexiones :refer [consulta-asistencial]]
            [honey.sql :as sql]
            [antoine.especificaciones.generadores :refer [generar-protocolo-internado-iniciado 
                                                          generar-protocolo-internado-con-implantes
                                                          generar-protocolo-internado-extraccion-implantes
                                                          generar-protocolo-internado-anatomia-patologica
                                                          legajo-sin-digito-verificador 
                                                          legajo-medico
                                                          generar-intervencion]]))

(defn insertar
  "Las opciones serían :hemodinamia y :parto, el status :inicializada :completa-con-implantes :completa-con-extraccion-implante 
   :completa-con-anatomia-patologica y anestesia? un booleano"
  [{:keys [tbc_admision_scroll/Adm_HistClin tbc_admision_scroll/Adm_FecIng tbc_admision_scroll/Adm_HorIng opciones status anestesia?] :as pac}]
  (let [{:keys [tipointerven parto_ neonatologia neonatologo]} (cond
                                                                 (= opciones :hemodinamia) {:tipointerven 2
                                                                                            :neonatologia (generar-intervencion)
                                                                                            :neonatologo 0
                                                                                            :parto_ 0}
                                                                 (= opciones :parto) {:tipointerven 0
                                                                                      :neonatologia (rand-nth [161 162 878])
                                                                                      :neonatologo (legajo-sin-digito-verificador legajo-medico)
                                                                                      :parto_ 1}
                                                                 :else (throw (IllegalArgumentException. "Opción inválida")))
        anestesia (if anestesia? 1 0)
        fn-generacion (cond
                        (= status :inicializada) generar-protocolo-internado-iniciado
                        (= status :completa-con-implantes) generar-protocolo-internado-con-implantes
                        (= status :completa-con-extraccion-implante) generar-protocolo-internado-extraccion-implantes
                        (= status :completa-con-anatomia-patologica) generar-protocolo-internado-anatomia-patologica
                        :else (throw (IllegalArgumentException. "Opción inválida")))
        valores (concat
                 [Adm_HistClin
                  Adm_FecIng
                  Adm_HorIng
                  Adm_FecIng
                  Adm_HorIng
                  tipointerven
                  anestesia
                  neonatologia
                  neonatologo
                  neonatologo
                  parto_]
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
                                        :cirinroquirofa
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