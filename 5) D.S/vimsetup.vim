cd W:/5) D.S/EncryptionAlgorithms/src/encryptionalgorithms/
set makeprg=javac
set wildignore=*.form
nnoremap <A-m> :lmake % \| :silent execute "!java %:r && pause" \|<Enter>

