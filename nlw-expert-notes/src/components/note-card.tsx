export function NoteCard() {
  return (
    <button className="rounded-md bg-slate-800 p-5 space-y-3 text-left overflow-hidden relative hover:ring-2 hover:ring-slate-600 focus-visible:ring-2 focus-visible:ring-lime-400 outline-none transition-all duration-200">
      <span className="text-sm font-medium text-slate-300">há 2 dias</span>

      <p className="text-sm leading-6 text-slate-400">
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Facere nihil
        obcaecati alias dolor cupiditate soluta expedita enim beatae esse, ipsam
        nulla nesciunt animi repudiandae magni dolores labore officiis, quidem
        voluptatibus! Lorem, ipsum dolor sit amet consectetur adipisicing elit.
        Facere nihil obcaecati alias dolor cupiditate soluta expedita enim
        beatae esse, ipsam nulla nesciunt animi repudiandae magni dolores labore
        officiis, quidem voluptatibus!
      </p>

      <div className="absolute bottom-0 left-0 right-0 h-1/2 bg-gradient-to-t from-black/60 to-black/0 pointer-events-none" />
    </button>
  );
}
