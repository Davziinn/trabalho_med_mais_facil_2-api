-- A triagem cria AGUARDANDO_TRIAGEM sem senha; o totem gera a senha no check-in.
-- Executar nos bancos existentes que ainda possuem a restrição NOT NULL.
BEGIN;
SET LOCAL lock_timeout = '5s';
ALTER TABLE public.tb_chamado ALTER COLUMN senha_fila DROP NOT NULL;
COMMIT;
