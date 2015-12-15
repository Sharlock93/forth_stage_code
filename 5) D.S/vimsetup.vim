cd "W:/5) D.S"
set makeprg=javac
set wildignore=*.form,*.class
nnoremap <A-m> :lmake % \| :silent execute "!java %:r && pause" \|<Enter>

