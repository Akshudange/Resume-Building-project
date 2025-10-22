
export default function Modal({ open, onClose, children }: any) {
  if (!open) return null;
  return (
    <div className="modal-overlay">
      <div className="modal">
        <button onClick={onClose}>✖</button>
        {children}
      </div>
    </div>
  );
}
