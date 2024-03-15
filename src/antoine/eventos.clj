(ns antoine.eventos
  (:require [antoine.sql.paciente_ambulatorio :as ambulatorio]
            [antoine.sql.paciente-internado :as internado]
            [antoine.sql.seguridad_quirurgica :as seguridad]
            [antoine.sql.alertas_asistenciales :as alerta]
            [antoine.sql.his_lectora :as lectora]
            [antoine.sql.anestesia_ambulatoria :as anes-ambu]))

(defn inicializar-paciente
  "Recibe una llave, :ambulatorio ó :internado"
  [tipo_paciente]
  (let [paciente (if (= tipo_paciente :ambulatorio) 
                   (-> (ambulatorio/paciente-ambulatorio-aleatorio)
                       (ambulatorio/limpiar-paciente {:Guar_Estado 1,
                                                      :Guar_Estado1 1,
                                                      :Guar_Estado3 1
                                                      :Guar_Medico 0,
                                                      :Guar_TipoMed 0,
                                                      :Guar_FechaAlta 0,
                                                      :Guar_HoraAlta 0,
                                                      :Guar_EspMed 0,
                                                      :Guar_HoraAtenc 0})) 
                   (-> (internado/paciente-internado-aleatorio)
                       (internado/limpiar-paciente)))]
    (-> paciente
        seguridad/borrar
        alerta/borrar
        (lectora/borrar 9999)
        anes-ambu/borrar)))