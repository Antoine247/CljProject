(ns antoine.comandos
  (:require [antoine.eventos :as e]
            [antoine.sql.seguridad-quirurgica :as sq]
            [antoine.sql.anestesia-internado :as anes-int]))

(defn crear_protocolo_internado 
  "Crea un protocolo de internación con el estado indicado por las banderas recibidas como argumento.
   aler -> Alerta asistencial
   ca -> Completo con anestesia
   csa -> Completo sin anestesia
   sqca -> Seguridad Quirurgica Completa con anestesia
   sqcsa -> Seguridad Quirurgica Completa sin anestesia
   sqia -> Seguridad Quirurgica antes incision con anestesia
   sqisa -> Seguridad Quirurgica antes incision sin anestesia
   eap -> Evaluacion anestesica preoperatoria
   fa -> Ficha anestesica
   fasc -> Ficha anestesica sin cerrar
   fasm -> Ficha anestesica sin medicamentos
   m -> Medicamentos cirugia
   hci -> Historia Clínica de Ingreso
   anatpa -> Anatomía Patológica
   cepa -> Con Evaluacion Post Anestesica
   rim -> Retiro de Implante
   imp -> Insercion de implante
   prto -> Parto
   hem -> Hemodinamia"
  [{:keys [aler ca csa sqca sqcsa sqia sqisa eap fa fasc fasm m hci anatpa cepa sepa rim imp prto hem]}]
  (cond
    sqca (-> (e/inicializar-paciente :internado)
             (assoc :tipo-solicitud :completa-con-anestesia)
             (sq/insertar))
    sqcsa (-> (e/inicializar-paciente :internado)
              (assoc :tipo-solicitud :completa-sin-anestesia)
              (sq/insertar))
    sqia (-> (e/inicializar-paciente :internado)
             (assoc :tipo-solicitud :parcial-con-anestesia)
             (sq/insertar))
    sqisa (-> (e/inicializar-paciente :internado)
              (assoc :tipo-solicitud :parcial-sin-anestesia)
              (sq/insertar))
    eap (-> (e/inicializar-paciente :internado)
            (assoc :tipo-insercion :preanestesica :tipo-solicitud :completa-con-anestesia)
            (sq/insertar)
            (anes-int/insertar))
    cepa (-> (e/inicializar-paciente :internado)
             (assoc :tipo-insercion :completa :tipo-solicitud :completa-con-anestesia) ;; Hay que agregar el registro en tbc_cirugint
             (sq/insertar)
             (anes-int/insertar))
    :else (throw (IllegalArgumentException. "Opción inválida"))))

(defn crear_protocolo_ambulatorio
  "Crea un protocolo de internación con el estado indicado por las banderas recibidas como argumento.
     aler -> Alerta asistencial
     ca -> Completo con anestesia
     csa -> Completo sin anestesia
     sqc -> Seguridad Quirurgica Completa
     sqi -> Seguridad Quirurgica antes incision
     eap -> Evaluacion anestesica preoperatoria
     fa -> Ficha anestesica
     fasc -> Ficha anestesica sin cerrar
     fasm -> Ficha anestesica sin medicamentos
     m -> Medicamentos cirugia
     hci -> Historia Clínica de Ingreso
     cepa -> Con Evaluacion Post Anestesica
     sepa -> Sin Evaluacion Post Anestesica
     rim -> Retiro de Implante
     imp -> Insercion de implante
     hem -> Hemodinamia"
  [{:keys [aler ca csa sqc sqi eap fa fasc fasm m hci cepa sepa rim imp hem]}])

(comment 
   ; Revisar fecha en anestesia, cambiar por fecha actual
  (crear_protocolo_internado {:cepa true})
  (crear_protocolo_internado {:sqca true})
    
  
 (def l '(3221930M
          20240612
          1752
          1
          0
          2762
          0
          966
          0
          2
          181
          163
          23
          "NORMAL"
          1
          1
          3
          1739
          ""
          ""
          ""
          ""
          ""
          ""
          "X"
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          "X"
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          "X"
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          "X"
          ""
          ""
          ""
          ""
          ""
          "X"
          ""
          ""
          ""
          ""
          ""
          "X"
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          ""
          "X"
          ""
          ""
          "X"
          ""
          ""
          ""
          0
          "ESTABLE"
          1
          1
          1
          1
          1
          1
          0
          1
          0
          2
          1809
          1
          0
          0
          1
          0
          1
          0
          0
          0
          1
          0
          0
          1
          3
          3
          3
          0
          0
          "")) 
  (count l)
  )