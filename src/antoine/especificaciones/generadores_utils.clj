(ns antoine.especificaciones.generadores-utils
  (:require  [clojure.spec.gen.alpha :as gen]
             [clojure.spec.alpha :as spec]
             [antoine.utils.utils :as utils]))
    
(def nombres
  #{"MIRANDA HECTOR ARTURO"         
   "REALI DE BETTINELLI EVA"       
   " MONTAÑEZ DE MENDOZA LIDIA"     
   " ALMIRON BREGORIA"              
   " REYNOSO MARTA DEL CARMEN"      
   " FERNANDEZ FRANCISCA"           
   " VEGA RAQUEL"                   
   " HERNANDEZ HAYDEE ANGELICA"     
   " PEREZ MORALES LILIA XIMENA"    
   " RAVA DE REY NORMA AIDA"        
   " LARROSA CASANAS BLANCA A."     
   " GOÑI IRIA LUZ"                 
   " GODOY BLANCA ROSA"             
   " JIMENEZ MARIA ANGELA"          
   " VELASCO MARIA TERESA"          
   " KREPKI OLGA"                   
   " VALLEJOS CLARA"                
   " CARPENTINO SUSANA LIDIA"       
   " BILA GRACIELA MARIA"           
   " VIERA ROSA"                    
   " GAYOSO DE CENTURION IGNACIA"   
   " SALEGA PEDRO"                  
   " CABRERA ANA MARIA"             
   " SILVA ESTELA ISABEL"           
   " ALEGRE DAMASIA"                
   " IBAÑEZ MIRTA LUCRECIA"         
   " VILLALBA SARA"                 
   " SOLIS ERNESTO"                 
   " GONZALEZ JUANA EDITTA"         
   " OLIVA DODDA MARGARITA MARIA"   
   " PAZ LUISA EVA"                 
   " VELAZQUEZ MIRTA"               
   " ORRILLO LILIA ROSA"            
   " QUILAQUEO DORALIZA"            
   " CERRANO CATALINA MARIA"        
   " OJEDA CRISTINA MIRTA"          
   " VILLANUEVA VICTORIA"           
   " GOLDMAN JOSE MARIO"            
   " RODRIGUEZ MARGARITA"           
   " VALLEGA COLMAN ALICIA"         
   " CACERES MARCELA GRISELDA"      
   " CACERES SARA AMELIA"           
   " VEGA AZUCENA DEL VALLE"        
   " MONTEROS ELENA"                
   " ALVAREZ MABEL DEL ROSARIO"     
   " MORENO DELIA MARTA"            
   " NEIRA TORRES AMERITA"          
   " MELO SILVERIA"                 
   " GINI CARLOS FERNANDO"          
   " DE JUANA OSVALDO ERNESTO"      
   " FLORES MARTA CECILIA"          
   " FASSIO ROSA MARIA"             
   " RAMOS JOSE CLAUDIO"            
   " GOSELLA JORGE EDUARDO"         
   " CARDOZO CONCEPCION"            
   " GODOY OLGA ESTER"              
   " TUMEZONEK CELESTINA CARMEN"    
   " MONTELEONE ANA TERESA"         
   " SAMPIETRO EDUARDO"             
   " PATELLI MARIA ESTHER"          
   " ESTIGARRIBIA TERESA A."        
   " GIMENEZ ANTONIA"               
   " ANGULO MONTECINOS DEBRA B."    
   " CARRAZANA MIRTA YOLANDA"       
   " CHOQUE LOPEZ LEONOR"           
   " CARRO GLADYS ESTHER"           
   " ACOSTA ELENA ELVIRA"           
   " CARDOZO CARMEN ROSA"           
   " ROJAS NESTOR ANDRES"           
   " CACERES LUISA GABRIELA"        
   " SOSA NANCY LILIANA"            
   " ANZE BELLIDO ELSA"             
   " ALVARADO GRACIELA BEATRIZ"     
   " VILLAR HILDA ESTER"            
   " PONCE LUZ DEL VALLE"           
   " PORCINO JOSEFA"                
   " SOSA MARIA ALEJANDRA"          
   " ANDRADA ROSA AURELIA"          
   " ZAYA FRANCISCA"                
   " REID BERTA NOEMI"              
   " GALVAN DORA ROSA"              
   " IGNACIO NIEVES"                
   " BENEGAS MARIA"                 
   " SOTELO GUMERCINDA"             
   " CAMPOS PAREDES YONI"           
   " MAMANI MARTA B"                
   " COSSO NIDIA"                   
   " VARGAS ROCHA MIRIAM"           
   " SOLIS JOSEFINA X"              
   " VERA MARGACHELA"               
   " GALEANO PATRICIA A"            
   " VIGEZZI ALICIA JUANA"          
   " CASAS RUBEN CARLOS"            
   " ROSTCOVSKY ALBERTO IGNACIO"    
   " SMOCOVICH CARLOS"              
   " CERDA NORA ANTONIA"            
   " RENAU RODOLFO SALVADOR"        
   " PLOS CARLOS ANTONIO"           
   " BARCELO CATALINA"              
   " ZACCAGNINO CARLOS ALBERTO"     
   " OCHOA GLORIA ARGENTINA"        
   " GOMEZ LILIANA ELSA X"          
   " YANACON SILVESTRE FABIAN"      
   " LUNARDON ROXANA"               
   " ALBERRO JOSE MARIA"            
   " GAGLIARDI MARIA ALEJANDRA"     
   " TAPIA ELBA SUSANA"             
   " BARREIRO MARIA CRISTINA"       
   " PANIAGUA GUSTAVO NESTOR"       
   " UBALTON SUSANA TERESA"         
   " GUANUCO INOCENCIA"             
   " DI PAOLO LILIANA"              
   " PERTICONE RUBEN"               
   " ALMADA BLANCA"                 
   " SALATE SUSANA"                 
   " GASPAR MARINA BEATRIZ"         
   " CATALAN ELENA NELIDA"          
   " FERNANDEZ NAVARRO GUSTAVO"     
   " MONTERO ROSA"                  
   " CARRIZO GABRIELA BEATRIZ"      
   " DURAND DIAZ FORTUNATA JULIA"   
   " MAURIZIO ESTELA BEATRIZ"       
   " SEOANE CLAUDIO ALEJANDRO"      
   " SANJURJO RODRIGUEZ MONICA"     
   " SABATINI MARTA ELENA"          
   " MEGYES LUIS DANIEL"            
   " GONZALEZ DE DOMINGUEZ AIDA"    
   " RIOS GLADYS LUCRESIA"          
   " CASTRO MARIA ROSA"             
   " VERA GRACIELA"                 
   " PRIMROSE DORIS SOFIA"          
   " RIOS MIRIAM"                   
   " TORRESE OSVALDO LUIS"          
   " GONZALEZ RODRIGUEZ ELSIDA"     
   " BARREIRO MARIA VALERIA"        
   " GRYNIEWICZ NORA"               
   " CARO SILVIA ADELA"             
   " ARES NORA"                     
   " CABAÑAS CASCO FACUNDA"         
   " GUZMAN ROSAS VIRGINIA"         
   " PALOMERO PATRICIA"             
   " BELLUSCI MARGARITA"            
   " DOPICO GLADYS FABIANA"         
   " HOYOS JUAN RAMON"              
   " RUIZ DOMINGA AURORA"           
   " LEON MARIA ELIANA"             
   " NOVAS JORGE ALBERTO"           
   " ROCCIOLINO MONICA"             
   " ROMERO MONICA GRACIELA"        
   " GOMEZ NELIDA"                  
   " REPETTI GABRIEL HORACIO"       
   " DETOMASI SANDRA VIVIANA"      
   " FERNANDEZ LIDIA RITA"          
   " BALDUZZI MARTA BEATRIZ"       
   " CENTENO CRISTINA"              
   " MARTINEZ GLADYS ELISABETH"     
   " HUACCA CORIMANYA ADELA LUZ"    
   " AREVALOS FERREIRA MARCELINA"   
   " AZANA NORMA NOEMI"              
   " CAMPO MARIELA ANDREA"})
 
(defn generar-nombre
  []
  (gen/generate (spec/gen nombres)))

(def legajos-personal #{10015
                        10016
                        10017
                        10018
                        10019
                        10022
                        10023
                        10028
                        10029
                        10030
                        10031
                        10032
                        10033
                        10034
                        10036
                        10037
                        10038
                        10039
                        10040
                        10041
                        10044
                        10045
                        10046
                        10048
                        10049
                        10057
                        10058
                        10059
                        10060
                        10065
                        10066
                        10067
                        10068
                        10069
                        10070
                        10071
                        10072
                        10073
                        10074
                        10075
                        10077
                        10078
                        10080
                        10081
                        10082
                        10083
                        10084
                        10085
                        10086
                        10087
                        10089
                        10090
                        10091
                        10092
                        10094
                        10095
                        10096
                        10097
                        10098
                        10099
                        10102
                        10103
                        10104
                        10105
                        10106
                        10107
                        10108
                        10113
                        10114
                        10117
                        10119
                        10120
                        10121
                        10122
                        10123
                        10124
                        10125
                        10126
                        10127
                        10133
                        10134
                        10137
                        10139
                        10140
                        10141
                        10145
                        10146
                        10147
                        10148
                        10149
                        10150
                        10153
                        10154
                        10156
                        10159
                        10160
                        10162
                        10165
                        10166
                        10167})

(def legajo-anestesiologo #{1660
                            29520
                            88030
                            27620
                            32290})

(def legajo-medico #{10020
                     10021
                     10024
                     10025
                     10026
                     10027
                     10035
                     10042
                     10043
                     10047
                     10050
                     10051
                     10052
                     10053
                     10054
                     10055
                     10056
                     10061
                     10062
                     10063
                     10064
                     10076
                     10079
                     10088
                     10093
                     10100
                     10101
                     10109
                     10110
                     10111
                     10112
                     10115
                     10116
                     10118
                     10128
                     10129
                     10130
                     10131
                     10132
                     10135
                     10136
                     10138
                     10142
                     10143
                     10144
                     10151
                     10152
                     10155
                     10157
                     10158
                     10161
                     10163
                     10164
                     10171
                     10175
                     10176
                     10180
                     10182
                     10183
                     10189
                     10193
                     10194
                     10195
                     10197
                     10198
                     10207
                     10209
                     10212
                     10213
                     10216
                     10218
                     10219
                     10226
                     10228
                     10229
                     10240
                     10243
                     10247
                     10248
                     10252
                     10254
                     10255
                     10256
                     10257
                     10258
                     10259
                     10260
                     10261
                     10262
                     10263
                     10264
                     10276
                     10279
                     10280
                     10281
                     10282
                     10283
                     10284
                     10285
                     10286
                     10288
                     10289
                     10290
                     10292
                     10293
                     10294
                     10295
                     10301
                     10303
                     10305
                     10306
                     10310
                     10311
                     10312
                     10313
                     10314
                     10315
                     10316
                     10318
                     10319
                     10320
                     10321
                     10323
                     10324
                     10325
                     10326
                     10327
                     10329
                     10330
                     10331
                     10337
                     10339
                     10340
                     10341
                     10342
                     10343
                     10344
                     10345
                     10348
                     10349
                     10358
                     10360
                     10361
                     10367
                     10368
                     10372
                     10373
                     10375
                     10376
                     10383
                     10385
                     10386
                     10396
                     10397
                     10407
                     10408
                     10409
                     10410
                     10411
                     10412
                     10417
                     10418
                     10424
                     10425
                     10427
                     10428
                     10429
                     10431
                     10432
                     10433
                     10434
                     10437
                     10441
                     10444
                     10445
                     10446
                     10447
                     10449
                     10454
                     10455
                     10456
                     10457
                     10458
                     10459
                     10460
                     10461
                     10462
                     10463
                     10467
                     10475
                     10479
                     10480
                     10483
                     10485
                     10486
                     10487
                     10488
                     10489
                     10490
                     10491})

(defn legajo-sin-digito-verificador
  [legajos]
  (-> (gen/generate (spec/gen legajos))
      utils/remover-digito-verificador))

(defn generar-intervencion
  []
  (let [interven [1
                  2
                  3
                  4
                  5
                  7
                  10
                  11
                  13
                  14
                  16
                  17
                  19
                  26
                  28
                  29
                  30
                  32
                  33
                  34
                  35
                  36
                  37
                  38
                  42
                  45
                  46
                  49
                  53
                  55
                  56
                  59
                  65
                  67
                  73
                  79
                  81
                  85
                  89
                  96
                  98
                  101
                  102
                  107
                  108
                  118
                  120
                  125
                  127
                  130
                  140
                  141
                  142
                  143
                  150
                  157
                  159
                  161
                  162
                  180
                  181
                  188
                  197
                  201
                  218
                  220
                  235
                  239
                  248
                  249
                  256
                  257
                  261
                  262
                  264
                  266
                  302
                  320
                  328
                  329
                  330
                  331
                  332
                  335
                  342
                  343
                  346
                  348
                  350
                  355
                  357
                  359
                  384
                  395
                  400
                  404
                  415
                  418
                  421
                  422
                  423
                  426
                  430
                  438
                  440
                  449
                  452
                  460
                  462
                  467
                  470
                  474
                  476
                  480
                  482
                  484
                  485
                  496
                  498
                  500
                  506
                  511
                  515
                  579
                  586
                  589
                  593
                  602
                  603
                  607
                  610
                  612
                  615
                  618
                  643
                  663
                  676
                  688
                  689
                  694
                  699
                  704
                  710
                  716
                  720
                  723
                  728
                  729
                  733
                  735
                  736
                  740
                  789
                  795
                  796
                  809
                  810
                  812
                  816
                  820
                  825
                  836
                  841
                  847
                  851
                  854
                  859
                  864
                  867
                  870
                  871
                  872
                  873
                  874
                  875
                  876
                  877
                  878
                  879
                  880
                  881
                  882
                  883
                  884
                  885
                  886
                  887
                  888
                  889
                  890
                  891
                  892
                  893
                  894
                  895
                  896
                  897
                  898
                  899
                  900
                  901
                  902
                  903
                  904
                  905
                  906
                  907
                  908
                  909
                  910
                  911
                  912
                  913
                  914
                  915
                  916
                  917
                  918
                  919
                  920
                  921
                  922
                  923
                  924
                  925
                  926
                  927
                  928
                  929
                  930
                  931
                  932
                  933
                  934
                  935
                  936
                  937
                  938
                  939
                  940
                  941
                  942
                  943
                  944
                  945
                  946
                  947
                  948
                  949
                  950
                  951
                  952
                  953
                  954
                  955
                  956
                  957
                  959
                  960
                  961
                  962
                  963
                  964
                  965
                  966
                  967
                  968
                  969
                  970
                  971
                  972
                  973
                  974
                  975
                  976
                  977
                  978
                  979
                  980
                  981
                  982
                  983
                  984
                  985
                  986
                  987
                  988
                  989
                  990
                  991
                  992
                  993
                  994
                  995
                  996
                  997
                  998
                  999
                  1000
                  1001
                  1002
                  1003
                  1004
                  1005
                  1006
                  1007
                  1008
                  1009
                  1010
                  1011
                  1012
                  1013
                  1014
                  1015
                  1016
                  1017
                  1018
                  1019
                  1020
                  1021
                  1022
                  1023
                  1024
                  1025
                  1026
                  1027
                  1028
                  1029
                  1030
                  1031
                  1032
                  1033
                  1034
                  1035
                  1036
                  1037
                  1038
                  1039
                  1040
                  1041
                  1042
                  1043
                  1044
                  1045
                  1046
                  1047
                  1048
                  1049
                  1050
                  1051
                  1052
                  1053
                  1054
                  1055
                  1056
                  1057
                  1058
                  1059
                  1060
                  1061
                  1062
                  1063
                  1065
                  1066
                  1067
                  1068
                  1069
                  1070
                  1071
                  1072
                  1073
                  1074
                  1075
                  1076
                  1077
                  1078
                  1079
                  1080
                  1081
                  1082
                  1083
                  1084
                  1085
                  1086
                  1087
                  1088
                  1089
                  1090
                  1091
                  1092
                  1093
                  1094
                  1095
                  1096
                  1097
                  1098
                  1099
                  1100
                  1101
                  1102
                  1103
                  1104
                  1105
                  1106
                  1107
                  1108
                  1109
                  1110
                  1111
                  1112
                  1113
                  1114
                  1115
                  1116
                  1117
                  1118
                  1119
                  1120
                  1121
                  1122
                  1123
                  1124
                  1125
                  1126
                  1127
                  1128
                  1129
                  1130
                  1131
                  1132
                  1133
                  1134
                  1135
                  1136
                  1137
                  1138
                  1139
                  1140
                  1141
                  1142
                  1143
                  1144
                  1145
                  1146
                  1147
                  1148
                  1149
                  1150
                  1151
                  1152
                  1153
                  1154
                  1163
                  1164
                  1170
                  1172
                  1173
                  1174
                  1177
                  1178
                  1182
                  1183
                  1186
                  1187
                  1188
                  1189
                  1192
                  1195
                  1200
                  1205
                  1208
                  1209
                  1210
                  1222
                  1227
                  1234
                  1235
                  1240
                  1245
                  1246
                  1252
                  1254
                  1255
                  1502
                  1518
                  1566
                  1684
                  1899
                  1965
                  2101
                  2145
                  2541
                  2896
                  3214
                  5897
                  7691
                  7777]]
    (rand-nth interven)))

(defn generar-presiones-arteriales
  []
  (let [sistolica (gen/generate (spec/gen :evaluacion-medica/presion-arterial))
        diastolica (gen/generate (spec/gen (spec/and :evaluacion-medica/presion-arterial #(not= % sistolica))))]
    {:sistolica sistolica
     :diastolica diastolica}))

(defn generar-pulso
  []
  (gen/generate (spec/gen :evaluacion-medica/presion-arterial)))

(def presion-min-max (generar-presiones-arteriales))

