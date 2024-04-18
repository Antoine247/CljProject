(ns antoine.eventos
  (:require [antoine.sql.paciente-ambulatorio :as ambulatorio]
            [antoine.sql.paciente-internado :as internado]
            [antoine.sql.seguridad-quirurgica :as seguridad]
            [antoine.sql.alertas-asistenciales :as alerta]
            [antoine.sql.his-lectora :as lectora]
            [antoine.sql.anestesia-internado :as anes]
            [antoine.especificaciones.generadores :refer [generar-intervencion]]
            [antoine.utils.utils :as utils]))
 
(defn inicializar-paciente
  "Recibe una llave, :ambulatorio ó :internado"
  [tipo_paciente]
  (if (= tipo_paciente :internado)
    (let [paciente (internado/paciente-internado-aleatorio)] 
      ((juxt seguridad/borrar alerta/borrar lectora/borrar anes/borrar) paciente)
      paciente)
    (let [paciente (ambulatorio/paciente-ambulatorio-aleatorio)
          fecha (utils/fecha-actual)
          hora (utils/hora-actual)
          updated-registro (-> (update paciente :tbc_guardia/Guar_FechaIngreso (fn [_] fecha))
                               (update :tbc_guardia/Guar_HoraIngreso (fn [_] hora)))
          {:keys [next.jdbc/update-count]} (-> paciente
                                               (ambulatorio/limpiar-paciente {:Guar_Estado 1
                                                                              :Guar_Estado1 1
                                                                              :Guar_Estado3 1
                                                                              :Guar_Medico 0
                                                                              :Guar_Especialidad 5
                                                                              :Guar_TipoMed 0
                                                                              :Guar_FechaAlta 0
                                                                              :Guar_HoraAlta 0
                                                                              :Guar_EspMed 0
                                                                              :Guar_HoraAtenc 0
                                                                              :Guar_FechaIngreso fecha
                                                                              :Guar_HoraIngreso hora
                                                                              :Guar_Diagnostico (generar-intervencion)}))]  ;;Revisar qué valor debe tener adm_utiliza
      (if (== update-count 1)
        updated-registro
        (throw (Exception. "Hubo un problema al actualizar el registro"))))))
 
(comment
    
  (inicializar-paciente :ambulatorio)
  (inicializar-paciente :internado)
   
  )