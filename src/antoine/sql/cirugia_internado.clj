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
                                                                generar-intervencion]]))

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
                  parto_]
                 (fn-generacion))
        nro_prot (nth valores 11)
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
   (when (consulta-asistencial consulta) #_(and (consulta-asistencial consulta) (anatpa/insertar pac))
     (assoc pac :tbc_cirugint/ciriprotocolo nro_prot))))
 
(comment

 (tap> (insertar {:tbc_admision_scroll/Adm_HistClin 3261570M
                  :tbc_admision_scroll/Adm_FecIng 20241016
                  :tbc_admision_scroll/Adm_HorIng 1848
                  :opciones nil
                  :status :inicializada
                  :anestesia? true}))

  (def campos '(cirihistcl, cirifechacarga, cirihoracarga, cirifechacarga1, cirihoracarga1, ciritipointerven, cirianestesia, ciriinterven, cirilegpartero, cirilegneonato, ciriparto, ciriprotocolo, ciriiepartero, ciriieneonato, ciripatologia, cirileghemote, cirilegmonito, cirilegperfus, cirilegconsenti, ciriieconsenti, ciriresponde, ciricodnome, ciritiponome, ciriieciru, cirilegciru, cirinroquirofa, cirifechafinal, cirihorafinal, cirifechainicio, cirihorainicio, ciriestado, ciriconsenti, ciriserieprot, cirimarcaprot, cirifechabanio, cirihorabanio, cirifecharasura, cirihorarasura, ciriconquerasura, cirinrosolpatol, ciriintensi, cirihorasint, ciricantint, ciriusoo2, ciriobsttipo, ciriobstgesta, cirilegatecnico, ciritipotecnico, ciripinzasinicio, ciripinzasfinal, cirigasasfinal, ciripasorend, ciriusolaserargon, ciriusomicroscopio, ciriusolaparascopio))
  (count campos)

  (def valor [3261570M
              20241016
              1848
              20241016
              1848
              1
              1
              1028
              0
              0
              0
              500524M
              0
              0
              0
              ""
              ""
              ""
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              ""
              ""
              ""
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0
              0])
  (count valor)

  (tap>
   (clojure.pprint/print-table (mapv merge
                                     (mapv #(assoc {} :campos %) (sort campos))
                                     (mapv #(assoc {} :columnas %) (sort valor)))))
  
  (tap> (zipmap campos valor))

  (count (list
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
          :ciriobsttipo 
          :ciriobstgesta
          :cirilegatecnico
          :ciritipotecnico
          :ciripinzasinicio
          :ciripinzasfinal
          :cirigasasfinal
          :ciripasorend
          :ciriusolaserargon
          :ciriusomicroscopio
          :ciriusolaparascopio))

  :rfc
  )