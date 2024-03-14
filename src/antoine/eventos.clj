(ns antoine.eventos
  (:require [antoine.sql.paciente_ambulatorio :as paciente]
            [antoine.sql.seguridad_quirurgica :as seguridad]
            [antoine.sql.alertas_asistenciales :as alerta]
            [antoine.sql.his_lectora :as lectora]
            [antoine.sql.anestesia_ambulatoria :as anes-ambu]))

(defn reinicio-paciente
  "llama a todas las funciones necesarias para borrar todos los registros de un paciente y poder usarlo desde 0
   puedes llamar la funcion sola y te va a usar un paciente random o puedes darle la historia clinica"
  [histcli]
  (let [paciente (paciente/limpiar-paciente (paciente/paciente-ambulatorio-aleatorio histcli) {:Guar_Estado 1,
                                                                                               :Guar_Estado1 1,
                                                                                               :Guar_Estado3 1
                                                                                               :Guar_Medico 0,
                                                                                               :Guar_TipoMed 0,
                                                                                               :Guar_FechaAlta 0,
                                                                                               :Guar_HoraAlta 0,
                                                                                               :Guar_EspMed 0,
                                                                                               :Guar_HoraAtenc 0})]
    (seguridad/borrar paciente)
    (alerta/borrar paciente)
    (lectora/borrar paciente 9999)
    (anes-ambu/borrar paciente)
    paciente))