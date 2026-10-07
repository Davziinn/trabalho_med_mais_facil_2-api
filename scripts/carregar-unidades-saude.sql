-- Carga local baseada no data.sql existente. Preserva registros e usa IDs reais.
BEGIN;
SET LOCAL lock_timeout = '5s';
LOCK TABLE tb_unidade_saude, tb_unid_sinto_atendi IN SHARE ROW EXCLUSIVE MODE;
CREATE TEMP TABLE seed_unidades (
 ordem integer GENERATED ALWAYS AS IDENTITY,
 nm_unid text, end_unid text, lat_unid double precision, long_unid double precision
) ON COMMIT DROP;
INSERT INTO seed_unidades (NM_UNID, END_UNID, LAT_UNID, LONG_UNID) VALUES
    -- ---- Blocos 1-10: UPAs 24h (emergência geral) ----
    ('UPA 24h - Edson Queiroz', 'Av. Contôrno, S/N, Edson Queiroz, Fortaleza - CE, 60812-035', -3.7705993, -38.4742712),
    ('UPA 24h - Praia do Futuro', 'R. Júlio Silva, 440, Praia do Futuro, Fortaleza - CE, 60182-435', -3.7446810, -38.4544873),
    ('UPA 24h - Itaperi', 'R. Betel, S/N, Rachel de Queiroz, Fortaleza - CE, 60714-315', -3.7956586, -38.5543715),
    ('UPA 24h - Autran Nunes', 'Av. Senador Fernandes Távora, S/N, Autran Nunes, Fortaleza - CE, 60526-642', -3.7561761, -38.5943138),
    ('UPA 24h - Cristo Redentor', 'Av. Pres. Castelo Branco, S/N, Cristo Redentor, Fortaleza - CE, 60312-060', -3.7088442, -38.5684410),
    ('UPA Bom Jardim', 'Rua Sargento João Pinheiro, S/N, Granja Lisboa, Fortaleza - CE, 61661-115', -3.7910194, -38.6247272),
    ('UPA 24h - Vila Velha', 'Av. L, S/N, Vila Velha, Fortaleza - CE, 60349-750', -3.7166042, -38.6017284),
    ('UPA 24h - Canindezinho', 'R. José Dantas Pereira, 447, Canindezinho, Fortaleza - CE, 60734-842', -3.8216760, -38.6090446),
    ('UPA 24h - José Walter', 'Av. Presidente Costa e Silva, S/N, Prefeito José Walter, Fortaleza - CE, 60761-190', -3.8257942, -38.5413223),
    ('UPA 24h - Jangurussu', 'Av. Contôrno Sul, S/N, Jangurussu, Fortaleza - CE, 60875-205', -3.8390641, -38.5225174),
    -- ---- Blocos 11-20: Hospitais gerais/distritais/universitários (emergência 24h) ----
    ('Instituto Doutor José Frota (IJF)', 'R. Barão do Rio Branco, 1816, Centro, Fortaleza - CE, 60025-061', -3.7344177, -38.5312350),
    ('Hospital Geral de Fortaleza (HGF)', 'R. Riachuelo, 900, Papicu, Fortaleza - CE, 60150-160', -3.7394693, -38.4763862),
    ('Hospital Geral Dr. César Cals', 'Avenida Imperador, 545, Centro, Fortaleza - CE, 60015-152', -3.7262679, -38.5340457),
    ('Hospital Estadual Leonardo da Vinci (HELV)', 'R. Rocha Lima, 1563, Aldeota, Fortaleza - CE, 60135-285', -3.7400923, -38.5100378),
    ('Hospital Universitário Walter Cantídio', 'R. Pastor Samuel Munguba, 1290, Rodolfo Teófilo, Fortaleza - CE, 60430-372', -3.7493236, -38.5517676),
    ('Hospital Universitário do Ceará (HUC/UECE)', 'R. Betel, 2021, Itaperi, Fortaleza - CE, 60714-230', -3.8004444, -38.5578935),
    ('Hospital Distrital Edmilson Barros de Oliveira (Frotinha Messejana)', 'Av. Jornalista Tomaz Coelho, 1578, Barroso, Fortaleza - CE, 60842-021', -3.8294174, -38.5091621),
    ('Frotinha da Parangaba', 'Av. Gen. Osório de Paiva, 1127, Parangaba, Fortaleza - CE, 60720-015', -3.7793274, -38.5719325),
    ('Hospital Distrital Gonzaga Mota Barra do Ceará', 'Av. Dom Aloísio Lorscheider, 1130, Vila Velha, Fortaleza - CE, 60347-780', -3.7151960, -38.5975644),
    ('Hospital Distrital Gonzaga Mota de Messejana', 'Av. Washington Soares, 7700, Messejana, Fortaleza - CE, 60844-150', -3.8221006, -38.4817028),
    -- ---- Bloco 21-22: unidades especializadas (não são emergência geral) ----
    -- ATENÇÃO: pediátrico e maternidade — ver nota de sintomas abaixo.
    ('Hospital Infantil Albert Sabin', 'R. Tertuliano Sales, 544, Vila União, Fortaleza - CE, 60410-794', -3.7629866, -38.5317353),
    ('Maternidade Escola Assis Chateaubriand', 'R. Coronel Nunes de Melo, S/N, Rodolfo Teófilo, Fortaleza - CE, 60430-270', -3.7483745, -38.5528780),
    -- ---- Blocos 23-50: Postos de Saúde / UBS (atenção primária, sem perfil de emergência 24h) ----
    ('Posto de Saúde Hélio Goes Ferreira', 'Av. Eng. Leal Lima Verde, 453, Sapiranga-Coité, Fortaleza - CE, 60833-520', -3.7909700, -38.4757325),
    ('Posto de Saúde Carlos Ribeiro', 'Av. Francisco Sá, 2670, Carlito Pamplona, Fortaleza - CE, 60310-000', -3.7199646, -38.5491486),
    ('Posto de Saúde Dr. Luís Costa', 'R. Jorge Dumar, 1501, Benfica, Fortaleza - CE, 60410-426', -3.7487014, -38.5383436),
    ('Posto de Saúde Maurício Mattos Dourado', 'R. Des. Floriano Benevides Magalhães, 391, Edson Queiroz, Fortaleza - CE, 60811-690', -3.7724456, -38.4788503),
    ('Posto de Saúde Irmã Hercília Aragão', 'Rua Frei Vidal, 1821, Tauape, Fortaleza - CE, 60120-100', -3.7559183, -38.5176311),
    ('Posto de Saúde Paulo Marcelo', 'R. Vinte e Cinco de Março, 607, Centro, Fortaleza - CE, 60060-120', -3.7293320, -38.5223823),
    ('Posto de Saúde Benedito Arthur de Carvalho', 'R. José Guilherme da Costa, 228, Eng. Luciano Cavalcante, Fortaleza - CE, 60811-320', -3.7692448, -38.4957738),
    ('Posto de Saúde Pio XII - Geraldo Madeira Sobrinho', 'R. Belisário Távora, 42, Tauape, Fortaleza - CE, 60130-830', -3.7558301, -38.5057752),
    ('Posto de Saúde Roberto da Silva Bruno', 'Av. Borges de Melo, 990, Fátima, Fortaleza - CE, 60415-762', -3.7599723, -38.5278739),
    ('Posto de Saúde Anastácio Magalhães', 'R. Delmiro de Farias, 1679, Rodolfo Teófilo, Fortaleza - CE, 60430-170', -3.7459213, -38.5525278),
    ('Posto de Saúde Célio Brasil Girão', 'R. Professor Henrique Firmeza, 82, Cais do Porto, Fortaleza - CE, 60180-760', -3.7181560, -38.4649093),
    ('Posto de Saúde Miriam Porto Mota', 'R. Cel. Jucá, 1636, Dionísio Torres, Fortaleza - CE, 60135-400', -3.7460155, -38.4941460),
    ('Posto de Saúde Rigoberto Romero', 'Av. das Graviolas, 195, Cidade 2000, Fortaleza - CE, 60190-600', -3.7519305, -38.4735969),
    ('Posto de Saúde Frei Tito', 'Tv. José Cláudio Gurgel Costa, 100, Praia do Futuro II, Fortaleza - CE, 60182-530', -3.7654758, -38.4404894),
    ('Posto de Saúde Aída Santos e Silva', 'Av. Trajano de Medeiros, 813, Vicente Pinzón, Fortaleza - CE, 60132-080', -3.7268587, -38.4662283),
    ('Posto de Saúde Argeu Herbster', 'R. Geraldo Barbosa, 1095, Bom Jardim, Fortaleza - CE, 60543-332', -3.7920439, -38.6021954),
    ('Posto de Saúde Gothardo Peixoto Figueiredo Lima', 'R. Irmã Bazet, 153, Damas, Fortaleza - CE, 60410-222', -3.7609772, -38.5556709),
    ('CAPS Ad SER IV', 'R. Betel, 1826, Itaperi, Fortaleza - CE, 60714-230', -3.7947539, -38.5542456),
    ('Posto de Saúde Ronaldo de Albuquerque Ribeiro', 'Av. I, 423, Conjunto Ceará I, Fortaleza - CE, 60533-671', -3.7678142, -38.5967195),
    ('Posto de Saúde Dr. João Elísio Holanda', 'R. C, Lot. Expedicionários I, 99, Mondubim, Fortaleza - CE, 60711-630', -3.8299017, -38.5902821),
    ('Posto de Saúde Dr. Luiz Augusto Castelo Branco (UAPS José Walter)', 'Av. João de Araújo Lima, 1631, Prefeito José Walter, Fortaleza - CE, 60750-740', -3.8362035, -38.5610649),
    ('Posto de Saúde Virgílio Távora', 'Av. Monsenhor Hélio Campos, 219, Cristo Redentor, Fortaleza - CE', -3.7052823, -38.5640961),
    ('Posto de Saúde Pedro Celestino Romero', 'R. Gastão Justa, 215, Maraponga, Fortaleza - CE, 60712-120', -3.8060279, -38.5695204),
    ('Posto de Saúde Francisco Domingos Da Silva', 'Av. Pres. Castelo Branco, 4759, Barra do Ceará, Fortaleza - CE, 60331-495', -3.7064010, -38.5743697),
    ('Posto de Saúde Edmar Fujita', 'Av. Alberto Craveiro, 1480, Boa Vista-Castelão, Fortaleza - CE, 60860-005', -3.7932870, -38.5180440),
    ('Posto de Saúde Terezinha Parente', 'R. Nelson Coelho, 215, Lagoa Redonda, Fortaleza - CE, 60831-410', -3.8271181, -38.4639183),
    ('Posto de Saúde Luis Franklin Pereira', 'R. Alexandre Vieira, S/N, Coaçu, Fortaleza - CE, 60871-750', -3.8375153, -38.4844895),
    ('Posto de Saúde Otoni Cardoso do Vale', 'Rua José Teixeira Costa, 643, São Bento, Fortaleza - CE, 60526-670', -3.8474912, -38.4806978);
INSERT INTO tb_unidade_saude (nm_unid,end_unid,lat_unid,long_unid)
SELECT s.nm_unid,s.end_unid,s.lat_unid,s.long_unid FROM seed_unidades s
WHERE NOT EXISTS (SELECT 1 FROM tb_unidade_saude u WHERE u.nm_unid=s.nm_unid AND u.end_unid=s.end_unid);
-- Mantém a associação dos primeiros 20 estabelecimentos definida no data.sql.
INSERT INTO tb_unid_sinto_atendi (unid_id,sint_atend)
SELECT u.id_unid_saud, sintomas.nome
FROM seed_unidades s
JOIN tb_unidade_saude u ON u.nm_unid=s.nm_unid AND u.end_unid=s.end_unid
CROSS JOIN (VALUES ('FEBRE'),('CEFALEIA'),('DOR_ABDOMINAL'),('DISPNEIA'),('DOR_TORACICA')) sintomas(nome)
WHERE s.ordem <= 20 AND NOT EXISTS (
 SELECT 1 FROM tb_unid_sinto_atendi a WHERE a.unid_id=u.id_unid_saud AND a.sint_atend=sintomas.nome
);
COMMIT;
